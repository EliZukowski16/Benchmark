package org.ssa.ironyard.benchmark.dao.orm;

import org.springframework.stereotype.Component;
import org.ssa.ironyard.benchmark.model.Language;

@Component
public class ORMLanguageImpl extends AbstractORM<Language> implements ORMLanguage
{    
    public ORMLanguageImpl()
    {    
        primaryKeys.add("id");
        fields.add("name");
    }
}
