package org.ssa.ironyard.benchmark.dao;

import java.util.List;

import org.ssa.ironyard.benchmark.model.DomainObject;

public abstract class AbstractORM<T extends DomainObject> implements ORM<T>
{
    protected String prepareSimpleQuery(String field)
    {
        return " SELECT " + projection() + " FROM " + table() + " WHERE " + field + " = ? ";
    }
    
    protected String prepareSimpleUpdate(List<String> fields)
    {
        String fieldNames = " SET ";
        for(int i = 0; i < fields.size(); i++)
        {
            fieldNames += (fields.get(i) + " = ?, ");
        }
        
        return " UPDATE " + this.table() + fieldNames + " WHERE id = ? ";
    }
    
    protected String prepareSimpleInsert(List<String> fields)
    {
        String fieldNames = " ( ";
        String values = " VALUES ( ";
        for(int i = 0; i <fields.size(); i++)
        {
            fieldNames += (fields.get(i)) + " , ";
            values += " ?, ";
        }
        fieldNames += " ) ";
        values += " ) ";
        
        return " INSERT INTO " + this.table() + fieldNames + values;
    }

}
