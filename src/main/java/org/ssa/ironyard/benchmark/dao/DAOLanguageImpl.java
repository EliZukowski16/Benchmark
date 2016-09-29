package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.ssa.ironyard.benchmark.dao.orm.ORMLanguageImpl;

public class DAOLanguageImpl extends AbstractDAOLanguage implements DAOLanguage
{

    protected DAOLanguageImpl(DataSource datasource)
    {
        super(datasource, new ORMLanguageImpl());
    }

}
