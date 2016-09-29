package org.ssa.ironyard.benchmark.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssa.ironyard.benchmark.dao.DAOBenchmarkEagerImpl;
import org.ssa.ironyard.benchmark.dao.DAOFrontEndServerImpl;
import org.ssa.ironyard.benchmark.dao.DAOLanguageImpl;
import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.model.Benchmark.Threads;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.FrontEndServer.FrontEndServerName;
import org.ssa.ironyard.benchmark.model.Language;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;


@Service
public class BenchmarkStarterService
{
    
    @Autowired
    DAOBenchmarkEagerImpl benchmarkDAO;
    
    @Autowired
    DAOLanguageImpl languageDAO;
    
    @Autowired
    DAOFrontEndServerImpl serverDAO;
    
    @Autowired
    public void initializeDatabase() throws IOException
    {
        BufferedReader reader = null;
        
        Map<String, Language> languagesInDB = new HashMap<>();
        Map<String, FrontEndServer> serversInDB = new HashMap<>();
        List<Benchmark> rawBenchmarks = new ArrayList<>();
        Set<FrontEndServer> rawServers = new HashSet<>();
        Set<Language> rawLanguages = new HashSet<>();
        
        try
        {
            reader = Files.newBufferedReader(Paths.get("./src/main/resources/benchmarks.csv"),
                    Charset.defaultCharset());

            String line;

            while (null != (line = reader.readLine()))
            {
                Map<Threads, BigInteger> performance = new HashMap<>();

                String[] benchmarks = line.split(",");

                Language testLanguage = new Language(LanguageName.getInstance(benchmarks[1]));
                

                FrontEndServer testServer = new FrontEndServer(FrontEndServerName.getInstance(benchmarks[2]));
                

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

                rawBenchmarks.add(benchmark);
                rawLanguages.add(testLanguage);
                rawServers.add(testServer);

            }
        }
        catch (Exception e)
        {
            System.err.println(e);
            throw(e);
        }
        finally
        {
            if (null != reader)
                reader.close();
        }
        
        for(Language l : rawLanguages)
        {
            languagesInDB.put(l.getLanguage().getName(), languageDAO.insert(l));
        }
        
        for(FrontEndServer s : rawServers)
        {
            serversInDB.put(s.getFrontEndServer().getName(), serverDAO.insert(s));
        }
        
        for (Benchmark b : rawBenchmarks)
        {

            Benchmark testBenchmark = b.clone();

            Language language = languagesInDB.get(b.getLanguage().getLanguage().getName());
            testBenchmark.setLanguage(language);

            FrontEndServer server = serversInDB.get(b.getFrontEndServer().getFrontEndServer().getName());
            testBenchmark.setFrontEndServer(server);

            benchmarkDAO.insert(testBenchmark);
        }
    }

}
