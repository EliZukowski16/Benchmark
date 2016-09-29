package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.ssa.ironyard.benchmark.dao.orm.ORMBenchmarkEagerImpl;

public class DAOBenchmarkEagerImpl extends AbstractDAOBenchmark implements DAOBenchmark
{

    protected DAOBenchmarkEagerImpl(DataSource datasource)
    {
        super(datasource, new ORMBenchmarkEagerImpl());
    }

}
