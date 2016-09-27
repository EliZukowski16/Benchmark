package org.ssa.ironyard.benchmark.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;

public class LanguageTest
{
    Language testLanguageOne;
    Language testLanguageTwo;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testHashCode()
    {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testLanguageLanguageName()
    {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testLanguageLanguageNameInteger()
    {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testGetLanguage()
    {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testClone()
    {
        testLanguageOne = new Language(LanguageName.NIM);
        testLanguageTwo = testLanguageOne.clone();
        
        assertEquals(testLanguageOne, testLanguageTwo);
        assertTrue(testLanguageOne.deeplyEquals(testLanguageTwo));
        assertFalse(testLanguageOne == testLanguageTwo);
        
        testLanguageOne.setLoaded(!testLanguageOne.isLoaded());
        
        assertEquals(testLanguageOne, testLanguageTwo);
        assertFalse(testLanguageOne.deeplyEquals(testLanguageTwo));
        assertFalse(testLanguageOne == testLanguageTwo);
    }

    @Test
    public void testDeeplyEquals()
    {
        testLanguageOne = new Language(null, 1);
        testLanguageTwo = new Language(null, 1);
        
        assertTrue(testLanguageOne.deeplyEquals(testLanguageTwo));
        
        testLanguageOne = new Language(LanguageName.DART, 1);
        
        assertFalse(testLanguageOne.deeplyEquals(testLanguageTwo));
        
        testLanguageTwo = new Language(LanguageName.DART, 1);
        
        assertTrue(testLanguageOne.deeplyEquals(testLanguageTwo));
        
        testLanguageOne = new Language(LanguageName.C, 1);
        
        assertFalse(testLanguageOne.deeplyEquals(testLanguageTwo));
        
        testLanguageTwo = new Language(LanguageName.C, 2);
        
        assertFalse(testLanguageOne.deeplyEquals(testLanguageTwo));
        
        testLanguageOne = new Language(null);
        testLanguageTwo = new Language(null);
        
        assertTrue(testLanguageOne.deeplyEquals(testLanguageTwo));
        
        testLanguageOne.setLoaded(!testLanguageOne.isLoaded());
        
        assertFalse(testLanguageOne.deeplyEquals(testLanguageTwo));
    }

    @Test
    public void testEqualsObject()
    {
        testLanguageOne = new Language(null, 1);
        testLanguageTwo = new Language(LanguageName.SCALA, 1);
        
        assertEquals(testLanguageOne, testLanguageTwo);
        
        testLanguageOne.setLoaded(!testLanguageOne.isLoaded());
        
        assertEquals(testLanguageOne, testLanguageTwo);
        
        testLanguageOne = new Language(LanguageName.SCALA, 2);
        
        assertNotEquals(testLanguageOne, testLanguageTwo);
        
        
        testLanguageOne = new Language(null);
        testLanguageTwo = new Language(null);
        
        assertEquals(testLanguageOne, testLanguageTwo);
        
        testLanguageOne.setLoaded(!testLanguageOne.isLoaded());
        
        assertEquals(testLanguageOne, testLanguageTwo);
    }

    @Test
    public void testSetLoaded()
    {
        testLanguageOne = new Language(LanguageName.LUA);
        
        testLanguageOne.setLoaded(true);
        assertTrue(testLanguageOne.isLoaded());
        assertEquals(null, testLanguageOne.getId());
        
        testLanguageOne.setLoaded(false);
        assertFalse(testLanguageOne.isLoaded());
        assertEquals(null, testLanguageOne.getId());
    }

    @Test
    public void testGetId()
    {
        testLanguageOne = new Language(LanguageName.C);
        
        assertEquals(null, testLanguageOne.getId());
        
        testLanguageOne = new Language(null);
        
        assertEquals(null, testLanguageOne.getId());
        
        testLanguageOne = new Language(null, 1);
        
        assertEquals(1, (int) testLanguageOne.getId());
        
        testLanguageOne = new Language(LanguageName.D, 1);
        
        assertEquals(1, (int) testLanguageOne.getId());
    }

    @Test
    public void testIsLoaded()
    {
        testLanguageOne = new Language(LanguageName.CLOJURE);
        
        assertFalse(testLanguageOne.isLoaded());
        
        testLanguageOne = new Language(LanguageName.C_PLUS_PLUS, 1);
        
        assertTrue(testLanguageOne.isLoaded());
        
        testLanguageOne = new Language(null);
        
        assertFalse(testLanguageOne.isLoaded());
        
        testLanguageOne = new Language(null, 1);
        
        assertTrue(testLanguageOne.isLoaded());
    }

}
