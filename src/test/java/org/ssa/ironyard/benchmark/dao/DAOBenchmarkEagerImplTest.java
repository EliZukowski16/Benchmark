package org.ssa.ironyard.benchmark.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.model.Benchmark.Threads;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.FrontEndServer.FrontEndServerName;
import org.ssa.ironyard.benchmark.model.Language;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DAOBenchmarkEagerImplTest extends AbstractDAOTest<Benchmark>
{
    static String URL = "jdbc:mysql://localhost/framework_benchmarks?user=root&password=root&useServerPrpStmts=true";
    static DataSource dataSource;

    AbstractDAO<Benchmark> benchmarkDAO;
    static AbstractDAO<FrontEndServer> serverDAO;
    static AbstractDAO<Language> languageDAO;

    Benchmark testBenchmark;
    static Language testLanguage;
    static FrontEndServer testServer;

    static List<Benchmark> rawTestBenchmarks;
    static Set<FrontEndServer> rawTestServers;
    static Set<Language> rawTestLanguages;

    List<Benchmark> benchmarksInDB;
    static Map<String, FrontEndServer> serversInDB;
    static Map<String, Language> languagesInDB;

    List<String> benchmarkNames;
    List<BigInteger> benchmarkErrors;
    List<Map<Threads, BigInteger>> benchmarkPerformance;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        MysqlDataSource mysqlDdataSource = new MysqlDataSource();
        mysqlDdataSource.setURL(URL);

        dataSource = mysqlDdataSource;
        serverDAO = new DAOFrontEndServerImpl(dataSource);
        languageDAO = new DAOLanguageImpl(dataSource);

        BufferedReader reader = null;
        rawTestBenchmarks = new ArrayList<>();
        rawTestServers = new HashSet<>();
        rawTestLanguages = new HashSet<>();

        serversInDB = new HashMap<>();
        languagesInDB = new HashMap<>();

        try
        {
            reader = Files.newBufferedReader(Paths.get("./src/main/resources/benchmarks.csv"),
                    Charset.defaultCharset());

            String line;

            while (null != (line = reader.readLine()))
            {
                Map<Threads, BigInteger> performance = new HashMap<>();

                String[] benchmarks = line.split(",");

                testLanguage = new Language(LanguageName.getInstance(benchmarks[1]));
                

                testServer = new FrontEndServer(FrontEndServerName.getInstance(benchmarks[2]));
                

                Benchmark benchmark = new Benchmark(benchmarks[0]);
                benchmark.setLanguage(testLanguage);
                benchmark.setFrontEndServer(testServer);
                performance.put(Threads._8, BigInteger.valueOf(Integer.parseInt(benchmarks[3])));
                performance.put(Threads._16, BigInteger.valueOf(Integer.parseInt(benchmarks[4])));
                performance.put(Threads._32, BigInteger.valueOf(Integer.parseInt(benchmarks[5])));
                performance.put(Threads._64, BigInteger.valueOf(Integer.parseInt(benchmarks[6])));
                performance.put(Threads._128, BigInteger.valueOf(Integer.parseInt(benchmarks[7])));
                performance.put(Threads._256, BigInteger.valueOf(Integer.parseInt(benchmarks[8])));
                benchmark.setPerformance(performance);
                benchmark.setErrors(BigInteger.valueOf(Integer.parseInt(benchmarks[9])));

                rawTestBenchmarks.add(benchmark);
                rawTestLanguages.add(testLanguage);
                rawTestServers.add(testServer);

            }
        }
        catch (Exception e)
        {
            System.err.println(e);
            throw e;
        }
        finally
        {
            if (null != reader)
                reader.close();
        }
        
        for(Language l : rawTestLanguages)
        {
            languagesInDB.put(l.getLanguage().getName(), languageDAO.insert(l));
        }
        
        for(FrontEndServer s : rawTestServers)
        {
            serversInDB.put(s.getFrontEndServer().getName(), serverDAO.insert(s));
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        languageDAO.clear();
        serverDAO.clear();
    }

    @Before
    public void setUpBenchmarkTests() throws Exception
    {
        this.benchmarkDAO = new DAOBenchmarkEagerImpl(dataSource);

        benchmarksInDB = new ArrayList<>();
        benchmarkNames = new ArrayList<>();
        benchmarkErrors = new ArrayList<>();
        benchmarkPerformance = new ArrayList<>();

        benchmarkDAO.clear();
        testLanguage = null;
        testServer = null;
    }

    @After
    public void tearDown() throws Exception
    {
        benchmarkDAO.clear();
        testLanguage = null;
        testServer = null;
    }

    public void loadBenchmarksIntoDB()
    {
        for (Benchmark b : rawTestBenchmarks)
        {
            benchmarkNames.add(b.getName());
            benchmarkErrors.add(b.getErrors());
            benchmarkPerformance.add(b.getPerformance());

            testBenchmark = b.clone();

            Language language = languagesInDB.get(b.getLanguage().getLanguage().getName());
            testBenchmark.setLanguage(language);

            FrontEndServer server = serversInDB.get(b.getFrontEndServer().getFrontEndServer().getName());
            testBenchmark.setFrontEndServer(server);

            benchmarksInDB.add(benchmarkDAO.insert(testBenchmark));
        }
    }

    @Test
    public void testRead()
    {
        List<Benchmark> benchmarksFromDB;

        this.loadBenchmarksIntoDB();

        benchmarksFromDB = ((DAOBenchmarkEagerImpl) benchmarkDAO).read();

        assertEquals(benchmarksInDB.size(), benchmarksFromDB.size());
        assertEquals(rawTestBenchmarks.size(), benchmarksFromDB.size());

        for (Benchmark b : benchmarksFromDB)
        {
            assertTrue(b.getId() != null);
            assertTrue(benchmarkNames.contains(b.getName()));
            assertTrue(languagesInDB.containsValue(b.getLanguage()));
            assertTrue(b.getLanguage().isLoaded());
            assertTrue(serversInDB.containsValue(b.getFrontEndServer()));
            assertTrue(b.getFrontEndServer().isLoaded());
            assertTrue(benchmarkPerformance.contains(b.getPerformance()));
            assertTrue(benchmarkErrors.contains(b.getErrors()));
            assertTrue(b.isLoaded());
        }
    }

    @Test
    public void testReadByBenchmark()
    {
        List<Benchmark> benchmarksFromDB;

        this.loadBenchmarksIntoDB();

        for (Benchmark b : rawTestBenchmarks)
        {
            benchmarksFromDB = ((DAOBenchmarkEagerImpl) benchmarkDAO).readByBenchmark(b);

            for (Benchmark c : benchmarksFromDB)
            {
                assertTrue(b.getName().equals(c.getName()));
                assertTrue(c.getId() != null);
                assertTrue(c.isLoaded());
            }
        }

    }

    @Test
    public void testReadByLanguage()
    {
        List<Benchmark> benchmarksFromDB;

        this.loadBenchmarksIntoDB();

        for (Entry<String, Language> e : languagesInDB.entrySet())
        {
            testLanguage = (Language) e.getValue();
            
            benchmarksFromDB = ((DAOBenchmarkEagerImpl) benchmarkDAO).readByLanguage((Language) e.getValue());

            for (Benchmark c : benchmarksFromDB)
            {
                assertTrue(testLanguage.equals(c.getLanguage()));
                assertTrue(testLanguage.deeplyEquals(c.getLanguage()));
                assertTrue(c.getId() != null);
                assertTrue(c.isLoaded());
            }
        }
    }

    @Test
    public void testReadByFrontEndServer()
    {
        List<Benchmark> benchmarksFromDB;

        this.loadBenchmarksIntoDB();

        for (Entry<String, FrontEndServer> e : serversInDB.entrySet())
        {
            testServer = (FrontEndServer) e.getValue();
            
            benchmarksFromDB = ((DAOBenchmarkEagerImpl) benchmarkDAO).readByFrontEndServer((FrontEndServer) e.getValue());

            for (Benchmark c : benchmarksFromDB)
            {
                assertTrue(testServer.equals(c.getFrontEndServer()));
                assertTrue(testServer.deeplyEquals(c.getFrontEndServer()));
                assertTrue(c.getId() != null);
                assertTrue(c.isLoaded());
            }
        }
    }

    @Override
    protected AbstractDAO<Benchmark> getDAO()
    {
        benchmarkDAO = new DAOBenchmarkEagerImpl(dataSource);
        
        return benchmarkDAO;
    }

    @Override
    protected Benchmark newInstance()
    {
        Benchmark benchmark =  new Benchmark("Test");
        benchmark.setLanguage(languagesInDB.get(LanguageName.C.getName()));
        benchmark.setFrontEndServer(serversInDB.get(FrontEndServerName.COWBOY.getName()));
        
        Map<Threads, BigInteger> performance = new HashMap<>();
        
        Integer i = 10;
        
        for(Threads t : Threads.values())
        {
            performance.put(t, BigInteger.valueOf(i));
            
            i = i * i;
        }
        
        benchmark.setPerformance(performance);
        benchmark.setErrors(BigInteger.valueOf(100));
        
        return benchmark;
        
    }

}
