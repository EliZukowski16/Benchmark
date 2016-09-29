package org.ssa.ironyard.benchmark.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.ssa.ironyard.benchmark.model.FrontEndServer.FrontEndServerName;

public class FrontEndServerTest
{

    FrontEndServer testServerOne;
    FrontEndServer testServerTwo;

    @Test
    public void testGetFrontEndServer()
    {
        for(FrontEndServerName f : FrontEndServerName.values())
        {
            testServerOne = new FrontEndServer(null);
            
            assertNotEquals(f, testServerOne.getFrontEndServer());
            assertEquals(null, testServerOne.getFrontEndServer());
            
            testServerOne = new FrontEndServer(f);
            
            assertEquals(f, testServerOne.getFrontEndServer());
            
            testServerOne = new FrontEndServer(f, 1);
            
            assertEquals(f, testServerOne.getFrontEndServer());
        }
    }

    @Test
    public void testClone()
    {
        testServerOne = new FrontEndServer(FrontEndServerName.BLAZE);
        testServerTwo = testServerOne.clone();
        
        assertEquals(testServerOne, testServerTwo);
        assertTrue(testServerOne.deeplyEquals(testServerTwo));
        assertFalse(testServerOne == testServerTwo);
        
        testServerOne.setLoaded(!testServerOne.isLoaded());
        
        assertEquals(testServerOne, testServerTwo);
        assertFalse(testServerOne.deeplyEquals(testServerTwo));
        assertFalse(testServerOne == testServerTwo);
        
        testServerTwo = new FrontEndServer(FrontEndServerName.COWBOY, 10);
        testServerOne = testServerTwo.clone();
        
        assertEquals(testServerOne, testServerTwo);
        assertTrue(testServerOne.deeplyEquals(testServerTwo));
        assertFalse(testServerOne == testServerTwo);
    }

    @Test
    public void testDeeplyEquals()
    {
        testServerOne = new FrontEndServer(null, 1);
        testServerTwo = new FrontEndServer(null, 1);
        
        assertTrue(testServerOne.deeplyEquals(testServerTwo));
        
        testServerOne = new FrontEndServer(FrontEndServerName.GUNICORN, 1);
        
        assertFalse(testServerOne.deeplyEquals(testServerTwo));
        
        testServerTwo = new FrontEndServer(FrontEndServerName.GUNICORN, 1);
        
        assertTrue(testServerOne.deeplyEquals(testServerTwo));
        
        testServerOne = new FrontEndServer(FrontEndServerName.MEINHELD, 1);
        
        assertFalse(testServerOne.deeplyEquals(testServerTwo));
        
        testServerTwo = new FrontEndServer(FrontEndServerName.MEINHELD, 2);
        
        assertFalse(testServerOne.deeplyEquals(testServerTwo));
        
        testServerOne = new FrontEndServer(null);
        testServerTwo = new FrontEndServer(null);
        
        assertTrue(testServerOne.deeplyEquals(testServerTwo));
        
        testServerOne.setLoaded(!testServerOne.isLoaded());
        
        assertFalse(testServerOne.deeplyEquals(testServerTwo));
    }

    @Test
    public void testEqualsObject()
    {
        testServerOne = new FrontEndServer(null, 1);
        testServerTwo = new FrontEndServer(FrontEndServerName.CHERRYPY, 1);
        
        assertNotEquals(testServerOne, testServerTwo);
        
        testServerOne.setLoaded(!testServerOne.isLoaded());
        
        assertNotEquals(testServerOne, testServerTwo);
        
        testServerOne = new FrontEndServer(FrontEndServerName.CHERRYPY, 2);
        
        assertNotEquals(testServerOne, testServerTwo);
        
        
        testServerOne = new FrontEndServer(null);
        testServerTwo = new FrontEndServer(null);
        
        assertEquals(testServerOne, testServerTwo);
        
        testServerOne.setLoaded(!testServerOne.isLoaded());
        
        assertEquals(testServerOne, testServerTwo);
    }

    @Test
    public void testSetLoaded()
    {
        testServerOne = new FrontEndServer(FrontEndServerName.DART);
        
        testServerOne.setLoaded(true);
        assertTrue(testServerOne.isLoaded());
        assertEquals(null, testServerOne.getId());
        
        testServerOne.setLoaded(false);
        assertFalse(testServerOne.isLoaded());
        assertEquals(null, testServerOne.getId());
    }

    @Test
    public void testGetId()
    {
        testServerOne = new FrontEndServer(FrontEndServerName.RESIN);
        
        assertEquals(null, testServerOne.getId());
        
        testServerOne = new FrontEndServer(null);
        
        assertEquals(null, testServerOne.getId());
        
        testServerOne = new FrontEndServer(null, 1);
        
        assertEquals(1, (int) testServerOne.getId());
        
        testServerOne = new FrontEndServer(FrontEndServerName.URWEB, 1);
        
        assertEquals(1, (int) testServerOne.getId());
    }

    @Test
    public void testIsLoaded()
    {
        testServerOne = new FrontEndServer(FrontEndServerName.TORQBOX);
        
        assertFalse(testServerOne.isLoaded());
        
        testServerOne = new FrontEndServer(FrontEndServerName.WILDFLY, 1);
        
        assertTrue(testServerOne.isLoaded());
        
        testServerOne = new FrontEndServer(null);
        
        assertFalse(testServerOne.isLoaded());
        
        testServerOne = new FrontEndServer(null, 1);
        
        assertTrue(testServerOne.isLoaded());
    }

}
