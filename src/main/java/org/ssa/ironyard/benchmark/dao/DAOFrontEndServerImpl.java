package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.ssa.ironyard.benchmark.dao.orm.ORM;
import org.ssa.ironyard.benchmark.model.FrontEndServer;

public class DAOFrontEndServerImpl extends AbstractDAOFrontEndServer implements DAOFrontEndServer
{

    protected DAOFrontEndServerImpl(DataSource datasource, ORM<FrontEndServer> orm)
    {
        super(datasource, orm);
    }

}
