package org.ssa.ironyard.benchmark.service;

import java.util.List;

import org.ssa.ironyard.benchmark.model.FrontEndServer;

public interface FrontEndServerService
{
    public List<FrontEndServer> getAllFrontEndServers();
    public List<FrontEndServer> getFrontEndServerByName(FrontEndServer server);
    public FrontEndServer getFrontEndServerByID(int serverID);
    public FrontEndServer addFrontEndServer(FrontEndServer server);
    public boolean deleteFrontEndServer(int serverID);
}
