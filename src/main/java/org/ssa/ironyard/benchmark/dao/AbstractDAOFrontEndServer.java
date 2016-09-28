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
import org.ssa.ironyard.benchmark.dao.orm.ORMFrontEndServer;
import org.ssa.ironyard.benchmark.dao.orm.ORMLanguage;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.FrontEndServer.FrontEndServerName;
import org.ssa.ironyard.benchmark.model.Language;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;

public abstract class AbstractDAOFrontEndServer extends AbstractDAO<FrontEndServer> implements DAOFrontEndServer
{

    protected AbstractDAOFrontEndServer(DataSource datasource, ORM<FrontEndServer> orm)
    {
        super(datasource, orm);
    }

    @Override
    public FrontEndServer insert(FrontEndServer domain)
    {
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            insertStmt = connection.prepareStatement(this.orm.prepareInsert(), Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, domain.getFrontEndServer().getName());
            insertStmt.executeUpdate();
            results = insertStmt.getGeneratedKeys();
            if (results.next())
            {
                FrontEndServer returnServer = new FrontEndServer(domain.getFrontEndServer(), results.getInt("id"));

                return returnServer;
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
    public List<FrontEndServer> read()
    {
        List<FrontEndServer> servers = new ArrayList<>();

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
                FrontEndServer returnServer = new FrontEndServer(FrontEndServerName.getInstance(results.getString("name")),
                        results.getInt("id"));

                servers.add(returnServer);
            }

            return servers;
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
    public List<FrontEndServer> readByFrontEndServer(FrontEndServer domain)
    {
        List<FrontEndServer> servers = new ArrayList<>();

        Connection connection = null;
        PreparedStatement readStmt = null;
        ResultSet results = null;

        try
        {   
            connection = datasource.getConnection();
            readStmt = connection.prepareStatement(((ORMFrontEndServer) this.orm).prepareReadByFrontEndServerName());
            readStmt.setString(1, domain.getFrontEndServer().getName());
            results = readStmt.executeQuery();
            while (results.next())
            {
                FrontEndServer returnServer = new FrontEndServer(FrontEndServerName.getInstance(results.getString("name")),
                        results.getInt("id"));

                servers.add(returnServer);
            }

            return servers;
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
    public FrontEndServer update(FrontEndServer domain)
    {
        Connection connection = null;
        PreparedStatement updateStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            updateStmt = connection.prepareStatement(this.orm.prepareUpdate(), Statement.RETURN_GENERATED_KEYS);
            updateStmt.setString(1, domain.getFrontEndServer().getName());
            updateStmt.setInt(2, domain.getId());
            if (updateStmt.executeUpdate() > 0)
            {
                FrontEndServer domainClone = domain.clone();
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
