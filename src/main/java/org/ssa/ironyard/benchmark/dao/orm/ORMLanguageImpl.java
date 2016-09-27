package org.ssa.ironyard.benchmark.dao.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ssa.ironyard.benchmark.model.Language;

public class ORMLanguageImpl extends AbstractORM<Language> implements ORMLanguage
{
    private final List<String> fields;
    private final List<String> primaryKeys;
    private final Map<String, String> foreignKeys;
    
    public ORMLanguageImpl()
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
