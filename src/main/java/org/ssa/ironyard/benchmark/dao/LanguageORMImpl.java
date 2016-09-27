package org.ssa.ironyard.benchmark.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ssa.ironyard.benchmark.model.Language;

public class LanguageORMImpl extends AbstractORM<Language> implements LanguageORM
{
    private final List<String> fields;
    private final List<String> primaryKeys;
    private final Map<String, String> foreignKeys;
    
    public LanguageORMImpl()
    {
        fields = new ArrayList<>();
        primaryKeys = new ArrayList<>();
        foreignKeys = new HashMap<>();
        
        primaryKeys.add("id");
        fields.add("language");
    }

    @Override
    public List<String> getFields()
    {
        return fields;
    }
    
    @Override
    public List<String> getPrimaryKeys()
    {
        return primaryKeys;
    }

    @Override
    public Map<String, String> getForeignKeys()
    {
        return foreignKeys;
    }

}
