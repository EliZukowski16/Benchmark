package org.ssa.ironyard.benchmark.dao;

import java.util.List;

import org.ssa.ironyard.benchmark.model.Benchmark;

public class BenchmarkORMEager extends AbstractORM<Benchmark> implements BenchmarkORM
{
    private List<String> fields;
    
    public BenchmarkORMEager()
    {
        fields.add("name");
    }
    
    @Override
    public String prepareInsert()
    {
        return prepareSimpleInsert(fields);
    }

    @Override
    public String prepareUpdate()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
