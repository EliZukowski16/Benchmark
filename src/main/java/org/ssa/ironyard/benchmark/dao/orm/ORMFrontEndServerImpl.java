package org.ssa.ironyard.benchmark.dao.orm;

import org.ssa.ironyard.benchmark.model.FrontEndServer;

public class ORMFrontEndServerImpl extends AbstractORM<FrontEndServer> implements ORMFrontEndServer
{
    public ORMFrontEndServerImpl()
    {        
        primaryKeys.add("id");
        fields.add("name");
    }
}
