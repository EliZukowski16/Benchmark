package org.ssa.ironyard.benchmark.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ssa.ironyard.benchmark.dao.orm.ORMFrontEndServerImpl;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.FrontEndServer.FrontEndServerName;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DAOFrontEndServerImplTest extends AbstractDAOTest<FrontEndServer>
{
    static String URL = "jdbc:mysql://localhost/framework_benchmarks?user=root&password=root&useServerPrpStmts=true";
    DataSource dataSource;
    AbstractDAO<FrontEndServer> serverDAO;
    FrontEndServer testLanguage;
    static Set<FrontEndServer> rawTestServers;
    List<FrontEndServer> serversInDB;
    List<String> serverNames;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        BufferedReader reader = null;
        rawTestServers = new HashSet<>();
        
        try
        {
            reader = Files.newBufferedReader(
                    Paths.get("./src/main/resources/benchmarks.csv"),
                    Charset.defaultCharset());
            
            String line;
            
            while (null !=(line = reader.readLine()))
            {
                String[] benchmarks = line.split(",");
                FrontEndServer server = new FrontEndServer(FrontEndServerName.getInstance(benchmarks[2]));
                
                rawTestServers.add(server);

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
        
    }

    @Before
    public void setUpFrontEndServerTests() throws Exception
    {
        MysqlDataSource mysqlDdataSource = new MysqlDataSource();
        mysqlDdataSource.setURL(URL);

        this.dataSource = mysqlDdataSource;

        this.serverDAO = new DAOFrontEndServerImpl(dataSource, new ORMFrontEndServerImpl());
        
        serverDAO.clear();
        
        serversInDB = new ArrayList<>();
        serverNames = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception
    {
        serverDAO.clear();
    }

    @Test
    public void testReadAllServers()
    {
        for(FrontEndServer f : rawTestServers)
        {
            serverDAO.insert(f);
            serverNames.add(f.getFrontEndServer().getName());
        }
        
        serversInDB = ((DAOFrontEndServerImpl) serverDAO).read();
        
        assertTrue(serversInDB.size() == rawTestServers.size());
        
        for(FrontEndServer l : serversInDB)
        {
            assertTrue(l.getId() != null);
            assertTrue(l.isLoaded());
            assertTrue(serverNames.contains(l.getFrontEndServer().getName()));
        }
    }
    
    @Test
    public void testReadByFrontEndServer()
    {   
        for(FrontEndServer f : rawTestServers)
        {
            serversInDB = new ArrayList<>();
            serverDAO.insert(f);
            serverNames.add(f.getFrontEndServer().getName());
            serversInDB = ((DAOFrontEndServerImpl)serverDAO).readByFrontEndServer(f);
            
            assertEquals(1, serversInDB.size());
            
            for(FrontEndServer k : serversInDB)
            {
                assertTrue(k.getId() != null);
                assertTrue(k.isLoaded());
                assertTrue(k.getFrontEndServer().equals(f.getFrontEndServer()));
                assertFalse(k.equals(f));
            }         
        }
    }

    @Override
    protected AbstractDAO<FrontEndServer> getDAO()
    {
        MysqlDataSource mysqlDdataSource = new MysqlDataSource();
        mysqlDdataSource.setURL(URL);
        
        this.dataSource = mysqlDdataSource;

        this.serverDAO = new DAOFrontEndServerImpl(dataSource, new ORMFrontEndServerImpl());
        
        return serverDAO;
    }

    @Override
    protected FrontEndServer newInstance()
    {
        return new FrontEndServer(FrontEndServerName.BLAZE);
    }

}
