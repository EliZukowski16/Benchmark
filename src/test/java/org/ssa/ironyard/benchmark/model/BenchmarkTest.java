package org.ssa.ironyard.benchmark.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.ssa.ironyard.benchmark.model.Benchmark.Threads;
import org.ssa.ironyard.benchmark.model.FrontEndServer.FrontEndServerName;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;

public class BenchmarkTest
{
    Benchmark testBenchmarkUnloaded;
    Benchmark testBenchmarkLoaded;

    FrontEndServer testFrontEndServerUnloaded;
    FrontEndServer testFrontEndServerLoaded;

    Language testLanguageUnloaded;
    Language testLanguageLoaded;
    
    Map<Threads, BigInteger> performance;

    @Before
    public void setUp() throws Exception
    {
        testBenchmarkUnloaded = new Benchmark("testOne");
        testBenchmarkLoaded = new Benchmark("testOne", 1);

        testLanguageUnloaded = new Language(LanguageName.C);
        testLanguageLoaded = new Language(LanguageName.C, 1);

        testFrontEndServerUnloaded = new FrontEndServer(FrontEndServerName.BLAZE);
        testFrontEndServerLoaded = new FrontEndServer(FrontEndServerName.BLAZE, 1);

        performance = new HashMap<>();

        int i = 10;

        for (Threads t : Threads.values())
        {
            performance.put(t, BigInteger.valueOf(i));
            
            i = i * i;
        }
    }


    @Test
    public void testGetName()
    {
        assertEquals("testOne", testBenchmarkUnloaded.getName());
        assertEquals("testOne", testBenchmarkLoaded.getName());
    }

    @Test
    public void testSetAndGetLanguage()
    {
        assertEquals(null, testBenchmarkUnloaded.getLanguage());

        testBenchmarkUnloaded.setLanguage(testLanguageUnloaded);

        assertEquals(testLanguageUnloaded, testBenchmarkUnloaded.getLanguage());
    }

    @Test
    public void testSetAndGetFrontEndServer()
    {
        assertEquals(null, testBenchmarkUnloaded.getFrontEndServer());

        testBenchmarkUnloaded.setFrontEndServer(testFrontEndServerUnloaded);

        assertEquals(testFrontEndServerUnloaded, testBenchmarkUnloaded.getFrontEndServer());
    }

    @Test
    public void testSetAndGetPerformance()
    {
        performance = new HashMap<>();

        assertEquals(0, testBenchmarkUnloaded.getPerformance().size());

        int i = 10;

        for (Threads t : Threads.values())
        {
            performance.put(t, BigInteger.valueOf(i));

            testBenchmarkLoaded.setPerformance(t, BigInteger.valueOf(i));

            i = i * i;
        }

        testBenchmarkUnloaded.setPerformance(performance);

        int j = 10;

        for (Threads t : Threads.values())
        {
            assertEquals(0, BigInteger.valueOf(j).compareTo(testBenchmarkUnloaded.getPerformance().get(t)));
            assertEquals(0, BigInteger.valueOf(j).compareTo(testBenchmarkLoaded.getPerformance().get(t)));

            j = j * j;
        }

    }

    @Test
    public void testSetAndGetErrors()
    {
        assertEquals(null, testBenchmarkUnloaded.getErrors());

        testBenchmarkUnloaded.setErrors(BigInteger.valueOf(100));

        assertEquals(BigInteger.valueOf(100), testBenchmarkUnloaded.getErrors());
    }

    @Test
    public void testClone()
    {
        assertNotEquals(testBenchmarkLoaded, testBenchmarkUnloaded);
        assertFalse(testBenchmarkLoaded.deeplyEquals(testBenchmarkUnloaded));
        assertFalse(testBenchmarkLoaded == testBenchmarkUnloaded);

        Benchmark testBenchmarkClone = testBenchmarkLoaded.clone();

        assertEquals(testBenchmarkClone, testBenchmarkLoaded);
        assertTrue(testBenchmarkClone.deeplyEquals(testBenchmarkLoaded));
        assertFalse(testBenchmarkClone == testBenchmarkLoaded);

        testBenchmarkClone.setLoaded(!testBenchmarkClone.isLoaded());

        assertEquals(testBenchmarkClone, testBenchmarkLoaded);
        assertFalse(testBenchmarkClone.deeplyEquals(testBenchmarkLoaded));
        assertFalse(testBenchmarkClone == testBenchmarkLoaded);

        testBenchmarkClone = testBenchmarkUnloaded.clone();

        assertEquals(testBenchmarkClone, testBenchmarkUnloaded);
        assertTrue(testBenchmarkClone.deeplyEquals(testBenchmarkUnloaded));
        assertFalse(testBenchmarkClone == testBenchmarkUnloaded);
    }

//    @Test
    public void testDeeplyEquals()
    {
        fail("Not yet implemented"); // TODO
    }

//    @Test
    public void testEqualsObject()
    {
        fail("Not yet implemented"); // TODO
    }

//    @Test
    public void testSetAndIsLoaded()
    {
        fail("Not yet implemented"); // TODO
    }

//    @Test
    public void testGetId()
    {
        fail("Not yet implemented"); // TODO
    }

}
