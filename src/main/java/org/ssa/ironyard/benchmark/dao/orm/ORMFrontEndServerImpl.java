package org.ssa.ironyard.benchmark.dao.orm;

import org.springframework.stereotype.Component;
import org.ssa.ironyard.benchmark.model.FrontEndServer;

@Component
public class ORMFrontEndServerImpl extends AbstractORM<FrontEndServer> implements ORMFrontEndServer
{
    public ORMFrontEndServerImpl()
    {        
        this.primaryKeys.add("id");
        this.fields.add("name");
    }
}
