package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.ssa.ironyard.benchmark.dao.orm.ORM;
import org.ssa.ironyard.benchmark.model.FrontEndServer;

public abstract class AbstractDAOFrontEndServer extends AbstractDAO<FrontEndServer> implements DAOFrontEndServer
{

    protected AbstractDAOFrontEndServer(DataSource datasource, ORM<FrontEndServer> orm)
    {
        super(datasource, orm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public FrontEndServer insert(FrontEndServer domain)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FrontEndServer update(FrontEndServer domain)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
