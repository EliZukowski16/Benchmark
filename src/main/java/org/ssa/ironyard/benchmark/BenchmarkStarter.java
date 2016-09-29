package org.ssa.ironyard.benchmark;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.ssa.ironyard.benchmark.service.BenchmarkStarterService;

/**
 *
 * @author thurston
 */
@SpringBootApplication
public class BenchmarkStarter
{
    static Logger LOGGER = LogManager.getLogger(BenchmarkStarter.class);

    public static void main(String[] args)
    {

        SpringApplication.run(BenchmarkStarter.class, args);

    }
}
