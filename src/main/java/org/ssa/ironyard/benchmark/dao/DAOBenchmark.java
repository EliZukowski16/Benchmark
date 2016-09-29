package org.ssa.ironyard.benchmark.dao;

import java.util.List;

import org.ssa.ironyard.benchmark.model.Benchmark;

public interface DAOBenchmark extends DAO<Benchmark>
{

    List<Benchmark> read();

    List<Benchmark> readByBenchmark(Benchmark domain);

}
