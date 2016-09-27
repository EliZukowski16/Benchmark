package org.ssa.ironyard.benchmark.dao;

import javax.sql.DataSource;

import org.ssa.ironyard.benchmark.dao.orm.ORM;
import org.ssa.ironyard.benchmark.model.Benchmark;

public abstract class AbstractDAOBenchmark extends AbstractDAO<Benchmark> implements DAOBenchmark
{

    protected AbstractDAOBenchmark(DataSource datasource, ORM<Benchmark> orm)
    {
        super(datasource, orm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Benchmark insert(Benchmark domain)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Benchmark update(Benchmark domain)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
