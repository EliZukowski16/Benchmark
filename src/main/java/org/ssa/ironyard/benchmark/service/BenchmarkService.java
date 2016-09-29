package org.ssa.ironyard.benchmark.service;

import java.util.List;

import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.Language;

public interface BenchmarkService
{
    public List<Benchmark> getAllBenchmarks();
    public List<Benchmark> getBenchmarksByBenchmarkName(Benchmark benchmark);
    public Benchmark getBenchmarkByBenchmarkID(int benchmarkID);
    public List<Benchmark> getBenchmarksByLanguage(Language language);
    public List<Benchmark> getBenchmarksByFrontEndServer(FrontEndServer server);
    public Benchmark addBenchmark(Benchmark benchmark);
}
