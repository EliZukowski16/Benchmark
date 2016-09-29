package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.ssa.ironyard.benchmark.dao.orm.ORMFrontEndServerImpl;

public class DAOFrontEndServerImpl extends AbstractDAOFrontEndServer implements DAOFrontEndServer
{

    protected DAOFrontEndServerImpl(DataSource datasource)
    {
        super(datasource, new ORMFrontEndServerImpl());
    }

}
