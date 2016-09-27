package org.ssa.ironyard.benchmark.dao.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.ssa.ironyard.benchmark.model.DomainObject;

public interface ORM<T extends DomainObject>
{
    public String projection();

    public String table();

    public T map(ResultSet results) throws SQLException;

    public String prepareInsert();

    public String prepareUpdate();
    
    public String prepareQuery();
    
    public void setQueryField(String query);
    
    public String getQueryField();

    default String prepareReadAll()
    {
        return " SELECT " + projection() + " FROM " + table();
    }

    default String prepareReadById()
    {
        return " SELECT " + projection() + " FROM " + table() + "WHERE id = ? ";
    }

    default String prepareDelete()
    {
        return " DELETE FROM " + table() + " WHERE id = ? ";
    }

    public List<String> getPrimaryKeys();
    
    public List<String> getFields();
    
    public Map<String, String> getForeignKeys();

    void addField(String field);

    void addPrimaryKey(String primaryKey);

    void addForeignKey(String foreignKeyTable, String foreignKeyName);
}
