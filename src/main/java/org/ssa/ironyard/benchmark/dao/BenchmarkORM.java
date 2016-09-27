package org.ssa.ironyard.benchmark.dao;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.model.Benchmark.Threads;

public interface BenchmarkORM extends ORM<Benchmark>
{
    default String projection()
    {
        return " id, name, language, front_end_server, 8_threads, 16_threads, 32_threads, 64_threads, 128_threads, "
                + " 256_threads, errors ";
    }

    default String table()
    {
        return " benchmarks ";
    }

    default Benchmark map(ResultSet results) throws SQLException
    {
        Benchmark benchmark = new Benchmark(results.getString("name"), results.getInt("id"));

        benchmark.setPerformance(Threads._8, BigInteger.valueOf(results.getInt("8_threads")));
        benchmark.setPerformance(Threads._16, BigInteger.valueOf(results.getInt("16_threads")));
        benchmark.setPerformance(Threads._32, BigInteger.valueOf(results.getInt("32_threads")));
        benchmark.setPerformance(Threads._64, BigInteger.valueOf(results.getInt("64_threads")));
        benchmark.setPerformance(Threads._128, BigInteger.valueOf(results.getInt("128_threads")));
        benchmark.setPerformance(Threads._256, BigInteger.valueOf(results.getInt("256_threads")));

        benchmark.setErrors(BigInteger.valueOf(results.getInt("errors")));

        return benchmark;
    }

}
