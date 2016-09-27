package org.ssa.ironyard.benchmark.dao.orm;

import org.ssa.ironyard.benchmark.model.Language;

public class ORMLanguageImpl extends AbstractORM<Language> implements ORMLanguage
{    
    public ORMLanguageImpl()
    {    
        primaryKeys.add("id");
        fields.add("language");
    }
}
