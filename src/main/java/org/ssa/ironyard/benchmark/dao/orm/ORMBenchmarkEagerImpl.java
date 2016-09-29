package org.ssa.ironyard.benchmark.dao.orm;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.model.Benchmark.Threads;
import org.ssa.ironyard.benchmark.model.FrontEndServer;
import org.ssa.ironyard.benchmark.model.FrontEndServer.FrontEndServerName;
import org.ssa.ironyard.benchmark.model.Language;
import org.ssa.ironyard.benchmark.model.Language.LanguageName;

public class ORMBenchmarkEagerImpl extends AbstractORM<Benchmark> implements ORMBenchmark
{
    private final ORM<FrontEndServer> frontEndServerORM;
    private final ORM<Language> languageORM;

    public ORMBenchmarkEagerImpl()
    {
        frontEndServerORM = new ORMFrontEndServerImpl();
        languageORM = new ORMLanguageImpl();

        primaryKeys.add("id");

        foreignKeys.put("languages", "language");
        foreignKeys.put("front_end_server", "front_end_server");

        fields.add("name");
        fields.add("language");
        fields.add("front_end_server");
        fields.add("8_threads");
        fields.add("16_threads");
        fields.add("32_threads");
        fields.add("64_threads");
        fields.add("128_threads");
        fields.add("256_threads");
        fields.add("errors");
    }

    @Override
    public String projection()
    {
        String projection = " ";
        for (int i = 0; i < primaryKeys.size(); i++)
            projection = projection + this.table() + "." + primaryKeys.get(i) + ", ";

        for (int i = 0; i < fields.size(); i++)
            projection = projection + this.table() + "." + fields.get(i) + ", ";

        for (int i = 0; i < languageORM.getFields().size(); i++)
            projection = projection + languageORM.table() + "." + languageORM.getFields().get(i) + ", ";

        for (int i = 0; i < frontEndServerORM.getFields().size(); i++)
            projection = projection + frontEndServerORM.table() + "." + frontEndServerORM.getFields().get(i) + ", ";

        projection = projection.substring(0, projection.length() - 2);

        return projection;
    }

    public Benchmark mapBenchmark(ResultSet results) throws SQLException
    {

        Benchmark benchmark;
        benchmark = new Benchmark(results.getString(this.table() + "." + this.getFields().get(0)),
                results.getInt(this.table() + "." + this.getPrimaryKeys().get(0)));

        benchmark.setFrontEndServer(this.mapFrontEndServer(results));
        benchmark.setLanguage(this.mapLanguage(results));

        benchmark.setErrors(BigInteger.valueOf(results.getInt(this.table() + "." + this.getFields().get(9))));

        for (int i = 3; i < 9; i++)
        {
            Threads threads = Threads.getInstance(this.getFields().get(i));

            benchmark.setPerformance(threads,
                    BigInteger.valueOf(results.getInt(this.table() + "." + this.getFields().get(i))));
        }

        return benchmark;
    }

    private FrontEndServer mapFrontEndServer(ResultSet results) throws SQLException
    {

        FrontEndServer frontEndServer;

        FrontEndServerName name = FrontEndServerName
                .getInstance(results.getString(frontEndServerORM.table() + "." + frontEndServerORM.getFields().get(0)));

        frontEndServer = new FrontEndServer(name,
                results.getInt(this.table() + "." + foreignKeys.get("front_end_server")));

        return frontEndServer;
    }

    private Language mapLanguage(ResultSet results) throws SQLException
    {

        Language language;

        LanguageName name = LanguageName
                .getInstance(results.getString(languageORM.table() + "." + languageORM.getFields().get(0)));

        language = new Language(name, results.getInt(this.table() + "." + foreignKeys.get("languages")));

        return language;
    }

    @Override
    public List<String> getFields()
    {
        return fields;
    }

    @Override
    public List<String> getPrimaryKeys()
    {
        return primaryKeys;
    }

    @Override
    public Map<String, String> getForeignKeys()
    {
        return foreignKeys;
    }

    @Override
    public String prepareReadAll()
    {
        return " SELECT " + projection() + " FROM " + this.table() + this.prepareJoin();
    }

    @Override
    public String prepareReadById()
    {
        return " SELECT " + this.projection() + " FROM " + this.table() + this.prepareJoin() + " WHERE " + this.table()
                + ".id = ? ";
    }

    @Override
    public String prepareReadByBenchmark()
    {
        return " SELECT " + this.projection() + " FROM " + this.table() + this.prepareJoin() + " WHERE " + this.table()
                + ".name = ? ";
    }

    @Override
    public String prepareReadByLanguage()
    {
        return " SELECT " + this.projection() + " FROM " + this.table() + this.prepareJoin() + " WHERE " + this.table()
                + ".language = ? ";
    }

    @Override
    public String prepareReadByFrontEndServer()
    {
        return " SELECT " + this.projection() + " FROM " + this.table() + this.prepareJoin() + " WHERE " + this.table()
                + ".front_end_server = ? ";
    }

    private String prepareJoin()
    {
        return this.joinLanguage() + this.relationshipLanguage() + this.joinFrontEndServer()
                + this.relationshipFrontEndServer();
    }

    private String joinLanguage()
    {
        return " " + "JOIN " + languageORM.table() + " ";
    }

    private String joinFrontEndServer()
    {
        return " " + " JOIN " + frontEndServerORM.table() + " ";
    }

    private String relationshipLanguage()
    {
        // Works for now, but need to generalize this expression
        return " ON " + this.table() + "." + this.getForeignKeys().get(languageORM.table()) + " = "
                + languageORM.table() + "." + languageORM.getPrimaryKeys().get(0);
    }

    private String relationshipFrontEndServer()
    {
        // Works for now, but need to generalize this expression
        return " ON " + this.table() + "." + this.getForeignKeys().get(frontEndServerORM.table()) + " = "
                + frontEndServerORM.table() + "." + frontEndServerORM.getPrimaryKeys().get(0);
    }

}
