package org.ssa.ironyard.benchmark.dao.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.FrontEndServer.FrontEndServerName;

public interface ORMFrontEndServer extends ORM<FrontEndServer>
{
    default String projection()
    {
        return "id, name";
    }
    
    default String table()
    {
        return "front_end_server";
    }
    
    default FrontEndServer map(ResultSet results) throws SQLException
    {
        return new FrontEndServer(FrontEndServerName.getInstance(results.getString("name")), results.getInt("id"));
    }
    
    default String prepareReadByFrontEndServerName()
    {
        return this.prepareQuery("name");
    }
    
//    default String prepareInsert()
//    {
//        return "INSERT INTO " + this.table() + " (name) VALUES (?) ";
//    }
    
//    default String prepareUpdate()
//    {
//        return "UPDATE " + table() + " SET name = ?, WHERE id = ?";
//    }
}
