package org.ssa.ironyard.benchmark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssa.ironyard.benchmark.dao.DAOBenchmarkEagerImpl;
import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.Language;

@Service
public class BenchmarkServiceImpl implements BenchmarkService
{
    @Autowired
    DAOBenchmarkEagerImpl benchmarkDAO;

    @Override
    public List<Benchmark> getAllBenchmarks()
    {
        return benchmarkDAO.read();
    }

    @Override
    public List<Benchmark> getBenchmarksByBenchmarkName(Benchmark benchmark)
    {
        return benchmarkDAO.readByBenchmark(benchmark);
    }

    @Override
    public Benchmark getBenchmarkByBenchmarkID(int benchmarkID)
    {
        return benchmarkDAO.read(benchmarkID);
    }

    @Override
    public List<Benchmark> getBenchmarksByLanguage(Language language)
    {
       return benchmarkDAO.readByLanguage(language);
    }

    @Override
    public List<Benchmark> getBenchmarksByFrontEndServer(FrontEndServer server)
    {
        return benchmarkDAO.readByFrontEndServer(server);
    }

    @Override
    public Benchmark addBenchmark(Benchmark benchmark)
    {
        return benchmarkDAO.insert(benchmark);
    }

}
