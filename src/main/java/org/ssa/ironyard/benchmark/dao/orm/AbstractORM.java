package org.ssa.ironyard.benchmark.dao.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ssa.ironyard.benchmark.model.DomainObject;

public abstract class AbstractORM<T extends DomainObject> implements ORM<T>
{
    protected String queryField;
    protected List<String> fields;
    protected List<String> primaryKeys;
    protected Map<String, String> foreignKeys;
    
    public AbstractORM()
    {
        fields = new ArrayList<>();
        primaryKeys = new ArrayList<>();
        foreignKeys = new HashMap<>();
    }
    
    @Override
    public List<String> getFields()
    {
        return fields;
    }
    
    @Override
    public void addField(String field)
    {
        fields.add(field);
    }

    @Override
    public List<String> getPrimaryKeys()
    {
        return primaryKeys;
    }
    
    @Override
    public void addPrimaryKey(String primaryKey)
    {
        primaryKeys.add(primaryKey);
    }

    @Override
    public Map<String, String> getForeignKeys()
    {
        return foreignKeys;
    }
    
    @Override
    public void addForeignKey(String foreignKeyTable, String foreignKeyName)
    {
        foreignKeys.put(foreignKeyTable, foreignKeyName);
    }
    
    @Override
    public String getQueryField()
    {
        return queryField;
    }
    
    @Override
    public void setQueryField(String query)
    {
        queryField = "";
        queryField = query;
    }
    
    @Override
    public String prepareQuery()
    {
        return " SELECT " + projection() + " FROM " + table() + " WHERE " + this.queryField + " = ? ";
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
