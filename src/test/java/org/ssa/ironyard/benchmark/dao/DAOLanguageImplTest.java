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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ssa.ironyard.benchmark.dao.orm.ORMLanguageImpl;
import org.ssa.ironyard.benchmark.model.Language;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DAOLanguageImplTest extends AbstractDAOTest<Language>
{
    static String URL = "jdbc:mysql://localhost/framework_benchmarks?user=root&password=root&useServerPrpStmts=true";
    DataSource dataSource;
    AbstractDAO<Language> languageDAO;
    Language testLanguage;
    static Set<Language> rawTestLanguages;
    List<Language> languagesInDB;
    List<String> languageNames;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        BufferedReader reader = null;
        rawTestLanguages = new HashSet<>();
        
        try
        {
            reader = Files.newBufferedReader(
                    Paths.get("./src/main/resources/benchmarks.csv"),
                    Charset.defaultCharset());
            
            String line;
            
            while (null !=(line = reader.readLine()))
            {
                String[] benchmarks = line.split(",");
                Language language = new Language(LanguageName.getInstance(benchmarks[1]));
                
                rawTestLanguages.add(language);

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
    public void setUpLanguageTests() throws Exception
    {
        MysqlDataSource mysqlDdataSource = new MysqlDataSource();
        mysqlDdataSource.setURL(URL);

        this.dataSource = mysqlDdataSource;

        this.languageDAO = new DAOLanguageImpl(dataSource);
        
        languageDAO.clear();
        
        languagesInDB = new ArrayList<>();
        languageNames = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception
    {
        languageDAO.clear();
    }

    @Test
    public void testReadAllLanguages()
    {   
        for(Language l : rawTestLanguages)
        {
            languageDAO.insert(l);
            languageNames.add(l.getLanguage().getName());
        }
        
        languagesInDB = ((DAOLanguageImpl) languageDAO).read();
        
        assertEquals(languagesInDB.size(), rawTestLanguages.size());
        
        for(Language l : languagesInDB)
        {
            assertTrue(l.getId() != null);
            assertTrue(l.isLoaded());
            assertTrue(languageNames.contains(l.getLanguage().getName()));
        }
        
    }

    @Test
    public void testReadByLanguage()
    {   
        for(Language l : rawTestLanguages)
        {
            languagesInDB = new ArrayList<>();
            languageDAO.insert(l);
            languageNames.add(l.getLanguage().getName());
            languagesInDB = ((DAOLanguageImpl)languageDAO).readByLanguage(l);
            
            assertEquals(1, languagesInDB.size());
            
            for(Language k : languagesInDB)
            {
                assertTrue(k.getId() != null);
                assertTrue(k.isLoaded());
                assertTrue(k.getLanguage().equals(l.getLanguage()));
                assertFalse(k.equals(l));
            }         
        }
    }

    @Override
    protected AbstractDAO<Language> getDAO()
    {
        MysqlDataSource mysqlDdataSource = new MysqlDataSource();
        mysqlDdataSource.setURL(URL);
        
        this.dataSource = mysqlDdataSource;

        this.languageDAO = new DAOLanguageImpl(dataSource);
        
        return languageDAO;
    }

    @Override
    protected Language newInstance()
    {
        return new Language(LanguageName.C);
    }

}
