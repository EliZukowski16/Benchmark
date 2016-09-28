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
import org.ssa.ironyard.benchmark.dao.orm.ORMLanguage;
import org.ssa.ironyard.benchmark.model.Language;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;

public abstract class AbstractDAOLanguage extends AbstractDAO<Language> implements DAOLanguage
{

    protected AbstractDAOLanguage(DataSource datasource, ORM<Language> orm)
    {
        super(datasource, orm);
    }

    @Override
    public Language insert(Language domain)
    {
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            insertStmt = connection.prepareStatement(this.orm.prepareInsert(), Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, domain.getLanguage().getName());
            insertStmt.executeUpdate();
            results = insertStmt.getGeneratedKeys();
            if (results.next())
            {
                Language returnLanguage = new Language(domain.getLanguage(), results.getInt(1));

                return returnLanguage;
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
    public Language update(Language domain)
    {
        Connection connection = null;
        PreparedStatement updateStmt = null;
        ResultSet results = null;

        try
        {
            connection = datasource.getConnection();
            updateStmt = connection.prepareStatement(this.orm.prepareUpdate(), Statement.RETURN_GENERATED_KEYS);
            updateStmt.setString(1, domain.getLanguage().getName());
            updateStmt.setInt(2, domain.getId());
            if (updateStmt.executeUpdate() > 0)
            {
                Language domainClone = domain.clone();
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

    @Override
    public List<Language> read()
    {
        List<Language> languages = new ArrayList<>();

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
                Language returnLanguage = new Language(LanguageName.getInstance(results.getString("name")),
                        results.getInt("id"));

                languages.add(returnLanguage);
            }

            return languages;
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
    public List<Language> readByLanguage(Language domain)
    {
        List<Language> languages = new ArrayList<>();

        Connection connection = null;
        PreparedStatement readStmt = null;
        ResultSet results = null;

        try
        {   
            connection = datasource.getConnection();
            readStmt = connection.prepareStatement(((ORMLanguage) this.orm).prepareReadByLanguage());
            readStmt.setString(1, domain.getLanguage().getName());
            results = readStmt.executeQuery();
            while (results.next())
            {
                Language returnLanguage = new Language(LanguageName.getInstance(results.getString("language")),
                        results.getInt("id"));

                languages.add(returnLanguage);
            }

            return languages;
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

}
