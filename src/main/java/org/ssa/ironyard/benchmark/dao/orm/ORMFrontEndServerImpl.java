package org.ssa.ironyard.benchmark.dao.orm;

import org.ssa.ironyard.benchmark.model.FrontEndServer;

public class ORMFrontEndServerImpl extends AbstractORM<FrontEndServer> implements ORMFrontEndServer
{
    public ORMFrontEndServerImpl()
    {        
        this.primaryKeys.add("id");
        this.fields.add("name");
    }
}
