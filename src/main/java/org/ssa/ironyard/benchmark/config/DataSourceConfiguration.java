package org.ssa.ironyard.benchmark.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author thurston
 */
@Configuration
public class DataSourceConfiguration 
{
    static final String DATABASE_URL = "jdbc:mysql://localhost/framework_benchmarks?user=root&password=root&" +
            "useServerPrpStmts=true";
    static final Logger LOGGER = LogManager.getLogger(DataSourceConfiguration.class);
    @Bean
    public DataSource datasource()
    {
        MysqlDataSource mysqlds = new MysqlDataSource();
        mysqlds.setURL(DATABASE_URL);
        return mysqlds;
    }


}