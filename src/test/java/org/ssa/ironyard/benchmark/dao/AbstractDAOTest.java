package org.ssa.ironyard.benchmark.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ssa.ironyard.benchmark.model.DomainObject;

public abstract class AbstractDAOTest<T extends DomainObject>
{

    protected abstract AbstractDAO<T> getDAO();

    protected abstract T newInstance();

    AbstractDAO<T> dao;

    @Before
    public void setUp() throws Exception
    {
        dao = getDAO();
    }

    @Test
    public void testCRUD()
    {
        T t = newInstance();

        T tInDB = dao.insert(t);

        T tFromDB = dao.read(tInDB.getId());
        
        assertTrue(tInDB.equals(tFromDB));

        assertTrue(tInDB.deeplyEquals(tFromDB));

        T tCloned = (T) tFromDB.clone();

        T tUpdated = dao.update(tCloned);

        assertEquals(tUpdated, tFromDB);
        assertTrue(tUpdated.deeplyEquals(tFromDB));

        assertTrue(dao.delete(tUpdated.getId()));

        assertFalse(dao.delete(tUpdated.getId()));
    }

}
