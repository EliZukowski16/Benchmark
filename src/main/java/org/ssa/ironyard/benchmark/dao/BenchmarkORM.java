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
        return " id, name, language, front_end_server, 8_thread, 16_thread, 32_thread, 64_thread, 128_thread, "
                + " 256_thread, errors ";
    }

    default String table()
    {
        return " benchmarks ";
    }

    default Benchmark map(ResultSet results) throws SQLException
    {
        Benchmark benchmark = new Benchmark(results.getString("name"), results.getInt("id"));

        benchmark.setPerformance(Threads._8, BigInteger.valueOf(results.getInt("8_thread")));
        benchmark.setPerformance(Threads._16, BigInteger.valueOf(results.getInt("16_thread")));
        benchmark.setPerformance(Threads._32, BigInteger.valueOf(results.getInt("32_thread")));
        benchmark.setPerformance(Threads._64, BigInteger.valueOf(results.getInt("64_thread")));
        benchmark.setPerformance(Threads._128, BigInteger.valueOf(results.getInt("128_thread")));
        benchmark.setPerformance(Threads._256, BigInteger.valueOf(results.getInt("256_thread")));

        benchmark.setErrors(BigInteger.valueOf(results.getInt("errors")));

        return benchmark;
    }

}
