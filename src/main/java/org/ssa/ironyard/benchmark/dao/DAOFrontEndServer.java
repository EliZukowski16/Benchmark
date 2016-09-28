package org.ssa.ironyard.benchmark.dao;

import java.util.List;

import org.ssa.ironyard.benchmark.model.FrontEndServer;

public interface DAOFrontEndServer extends DAO<FrontEndServer>
{
    List<FrontEndServer> read();
    List<FrontEndServer> readByFrontEndServer(FrontEndServer frontEndServer);
}
