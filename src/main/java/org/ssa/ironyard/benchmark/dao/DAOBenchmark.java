package org.ssa.ironyard.benchmark.dao;

import java.util.List;

import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.Language;

public interface DAOBenchmark extends DAO<Benchmark>
{

    List<Benchmark> read();

    List<Benchmark> readByBenchmark(Benchmark domain);

    List<Benchmark> readByLanguage(Language domain);

    List<Benchmark> readByFrontEndServer(FrontEndServer domain);

}
