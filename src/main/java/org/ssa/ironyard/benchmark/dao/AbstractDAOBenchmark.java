package org.ssa.ironyard.benchmark.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.ssa.ironyard.benchmark.dao.orm.ORM;
import org.ssa.ironyard.benchmark.dao.orm.ORMBenchmark;
import org.ssa.ironyard.benchmark.dao.orm.ORMBenchmarkEagerImpl;
import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.model.Benchmark.Threads;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.Language;

public abstract class AbstractDAOBenchmark extends AbstractDAO<Benchmark> implements DAOBenchmark
{

    protected AbstractDAOBenchmark(DataSource datasource, ORM<Benchmark> orm)
    {
        super(datasource, orm);
    }

    @Override
    public Benchmark insert(Benchmark domain)
    {
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            insertStmt = connection.prepareStatement(this.orm.prepareInsert(), Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, domain.getName());
            insertStmt.setInt(2, domain.getLanguage().getId());
            insertStmt.setInt(3, domain.getFrontEndServer().getId());
            insertStmt.setInt(4, domain.getPerformance().get(Threads._8).intValue());
            insertStmt.setInt(5, domain.getPerformance().get(Threads._16).intValue());
            insertStmt.setInt(6, domain.getPerformance().get(Threads._32).intValue());
            insertStmt.setInt(7, domain.getPerformance().get(Threads._64).intValue());
            insertStmt.setInt(8, domain.getPerformance().get(Threads._128).intValue());
            insertStmt.setInt(9, domain.getPerformance().get(Threads._256).intValue());
            insertStmt.setInt(10, domain.getErrors().intValue());

            insertStmt.executeUpdate();
            results = insertStmt.getGeneratedKeys();
            if (results.next())
            {
                Benchmark returnBenchmark = new Benchmark(domain.getName(), results.getInt(1));
                returnBenchmark.setFrontEndServer(domain.getFrontEndServer());
                returnBenchmark.setLanguage(domain.getLanguage());
                returnBenchmark.setPerformance(domain.getPerformance());
                returnBenchmark.setErrors(domain.getErrors());

                return returnBenchmark;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            cleanup(results, insertStmt, connection);
        }

        return null;
    }

    @Override
    public List<Benchmark> read()
    {
        List<Benchmark> benchmarks = new ArrayList<>();

        Connection connection = null;
        PreparedStatement readStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            readStmt = connection.prepareStatement(this.orm.prepareReadAll(), Statement.RETURN_GENERATED_KEYS);
            
            results = readStmt.executeQuery();
            while (results.next())
            {
                Benchmark returnBenchmark = ((ORMBenchmarkEagerImpl) this.orm).mapBenchmark(results);

                benchmarks.add(returnBenchmark);
            }

            return benchmarks;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            cleanup(results, readStmt, connection);
        }

        return null;
    }
    
    @Override
    public Benchmark read(int id)
    {
        Connection connection = null;
        PreparedStatement readStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            readStmt = connection.prepareStatement(((ORMBenchmark) this.orm).prepareReadById());
            readStmt.setInt(1, id);
            results = readStmt.executeQuery();
            if (results.next())
            {
                Benchmark returnBenchmark = ((ORMBenchmarkEagerImpl) this.orm).mapBenchmark(results);

                return returnBenchmark;
            }

            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            cleanup(results, readStmt, connection);
        }

        return null;
        
    }

    @Override
    public List<Benchmark> readByBenchmark(Benchmark domain)
    {
        List<Benchmark> benchmarks = new ArrayList<>();

        Connection connection = null;
        PreparedStatement readStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            readStmt = connection.prepareStatement(((ORMBenchmark) this.orm).prepareReadByBenchmark());
            readStmt.setString(1, domain.getName());
            results = readStmt.executeQuery();
            while (results.next())
            {
                Benchmark returnBenchmark = ((ORMBenchmarkEagerImpl) this.orm).mapBenchmark(results);

                benchmarks.add(returnBenchmark);
            }

            return benchmarks;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            cleanup(results, readStmt, connection);
        }

        return null;
    }
    
    @Override
    public List<Benchmark> readByLanguage(Language domain)
    {
        List<Benchmark> benchmarks = new ArrayList<>();

        Connection connection = null;
        PreparedStatement readStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            readStmt = connection.prepareStatement(((ORMBenchmark) this.orm).prepareReadByLanguage());
            readStmt.setInt(1, domain.getId());
            results = readStmt.executeQuery();
            while (results.next())
            {
                Benchmark returnBenchmark = ((ORMBenchmarkEagerImpl) this.orm).mapBenchmark(results);

                benchmarks.add(returnBenchmark);
            }

            return benchmarks;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            cleanup(results, readStmt, connection);
        }

        return null;
    }
    
    @Override
    public List<Benchmark> readByFrontEndServer(FrontEndServer domain)
    {
        List<Benchmark> benchmarks = new ArrayList<>();

        Connection connection = null;
        PreparedStatement readStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            readStmt = connection.prepareStatement(((ORMBenchmark) this.orm).prepareReadByFrontEndServer());
            readStmt.setInt(1, domain.getId());
            results = readStmt.executeQuery();
            while (results.next())
            {
                Benchmark returnBenchmark = ((ORMBenchmarkEagerImpl) this.orm).mapBenchmark(results);

                benchmarks.add(returnBenchmark);
            }

            return benchmarks;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            cleanup(results, readStmt, connection);
        }

        return null;
    }

    @Override
    public Benchmark update(Benchmark domain)
    {
        Connection connection = null;
        PreparedStatement updateStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            updateStmt = connection.prepareStatement(this.orm.prepareUpdate(), Statement.RETURN_GENERATED_KEYS);
            updateStmt.setString(1, domain.getName());
            updateStmt.setInt(2, domain.getLanguage().getId());
            updateStmt.setInt(3, domain.getFrontEndServer().getId());
            updateStmt.setInt(4, domain.getPerformance().get(Threads._8).intValue());
            updateStmt.setInt(5, domain.getPerformance().get(Threads._16).intValue());
            updateStmt.setInt(6, domain.getPerformance().get(Threads._32).intValue());
            updateStmt.setInt(7, domain.getPerformance().get(Threads._64).intValue());
            updateStmt.setInt(8, domain.getPerformance().get(Threads._128).intValue());
            updateStmt.setInt(9, domain.getPerformance().get(Threads._256).intValue());
            updateStmt.setInt(10, domain.getErrors().intValue());
            updateStmt.setInt(11, domain.getId());
            if (updateStmt.executeUpdate() > 0)
            {
                Benchmark domainClone = domain.clone();
                domainClone.setLoaded(true);

                return domainClone;
            }

            return null;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            cleanup(results, updateStmt, connection);
        }

        return null;

    }

}
