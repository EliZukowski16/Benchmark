package org.ssa.ironyard.benchmark.dao.orm;

import java.util.List;

import org.ssa.ironyard.benchmark.model.DomainObject;

public abstract class AbstractORM<T extends DomainObject> implements ORM<T>
{
    protected String queryField;
    protected List<String> fields;
    
    @Override
    public String prepareQuery()
    {
        return " SELECT " + projection() + " FROM " + table() + " WHERE " + this.queryField + " = ? ";
    }
    
    @Override
    public void setQueryField(String query)
    {
        queryField = "";
        queryField = query;
    }
    
    @Override
    public String getQueryField()
    {
        return queryField;
    }
    
    @Override
    public String prepareUpdate()
    {
        String fieldNames = " SET ";
        for(int i = 0; i < this.fields.size(); i++)
        {
            fieldNames += (this.fields.get(i) + " = ?, ");
        }
        
        return " UPDATE " + this.table() + fieldNames + " WHERE id = ? ";
    }
    
    @Override
    public String prepareInsert()
    {
        String fieldNames = " ( ";
        String values = " VALUES ( ";
        for(int i = 0; i <this.fields.size(); i++)
        {
            fieldNames += (this.fields.get(i)) + " , ";
            values += " ?, ";
        }
        fieldNames += " ) ";
        values += " ) ";
        
        return " INSERT INTO " + this.table() + fieldNames + values;
    }

}
