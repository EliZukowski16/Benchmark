package org.ssa.ironyard.benchmark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ssa.ironyard.benchmark.model.Language;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;

public interface LanguageORM extends ORM<Language>
{
    default String projection()
    {
        return "id, language";
    }
    
    default String table()
    {
        return "languages";
    }
    
    default Language map(ResultSet results) throws SQLException
    {
        return new Language(LanguageName.getInstance(results.getString("language")), results.getInt("id"));
    }
    
    default String prepareInsert()
    {
        return "INSERT INTO " + this.table() + " (language) VALUES (?) ";
    }
    
    default String prepareUpdate()
    {
        return "UPDATE " + table() + " SET language = ?, WHERE id = ?";
    }
}
