package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.ssa.ironyard.benchmark.dao.orm.ORM;
import org.ssa.ironyard.benchmark.model.Language;

public class DAOLanguageImpl extends AbstractDAOLanguage implements DAOLanguage
{

    protected DAOLanguageImpl(DataSource datasource, ORM<Language> orm)
    {
        super(datasource, orm);
    }

}
