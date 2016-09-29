package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.ssa.ironyard.benchmark.dao.orm.ORMBenchmarkEagerImpl;

@Repository
public class DAOBenchmarkEagerImpl extends AbstractDAOBenchmark implements DAOBenchmark
{

    protected DAOBenchmarkEagerImpl(DataSource datasource)
    {
        super(datasource, new ORMBenchmarkEagerImpl());
    }

}
