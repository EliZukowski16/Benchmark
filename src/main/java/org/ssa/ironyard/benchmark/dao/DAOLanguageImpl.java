package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.ssa.ironyard.benchmark.dao.orm.ORMLanguageImpl;

@Repository
public class DAOLanguageImpl extends AbstractDAOLanguage implements DAOLanguage
{

    protected DAOLanguageImpl(DataSource datasource)
    {
        super(datasource, new ORMLanguageImpl());
    }

}
