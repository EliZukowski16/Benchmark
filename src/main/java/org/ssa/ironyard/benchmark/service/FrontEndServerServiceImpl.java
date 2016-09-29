package org.ssa.ironyard.benchmark.service;

import java.util.List;

import org.ssa.ironyard.benchmark.dao.DAOFrontEndServerImpl;
import org.ssa.ironyard.benchmark.model.FrontEndServer;

public class FrontEndServerServiceImpl implements FrontEndServerService
{
    DAOFrontEndServerImpl serverDAO;

    @Override
    public List<FrontEndServer> getAllFrontEndServers()
    {
        return serverDAO.read();
    }

    @Override
    public List<FrontEndServer> getFrontEndServerByName(FrontEndServer server)
    {
        return serverDAO.readByFrontEndServer(server);
    }

    @Override
    public FrontEndServer getFrontEndServerByID(int serverID)
    {
        return serverDAO.read(serverID);
    }

    @Override
    public FrontEndServer addFrontEndServer(FrontEndServer server)
    {
        return serverDAO.insert(server);
    }

    @Override
    public boolean deleteFrontEndServer(int serverID)
    {
       return serverDAO.delete(serverID);
    }

}
