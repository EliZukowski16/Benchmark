package org.ssa.ironyard.benchmark.model;

public class FrontEndServer extends AbstractDomainObject implements DomainObject
{
    final FrontEndServerName frontEndServer;
    
    public enum FrontEndServerName
    {
        BLAZE("blaze"), COWBOY("cowboy"), GRIZZLY("grizzly"), GUNICORN("gunicorn"), HPHP("hphp"),
        HTTPSYS("httpsys"), HYPER("hyper"), HYPNOTOAD("hypnotoad"), IIS("iis"), JETTY("jetty"),
        LWAN("lwan"), MEINHELD("meinheld"), MONGREL2("mongrel2"), MONKEY("monkey"), NETTY("netty"),
        NGINX("nginx"), NGINXUWSGI("nginxuwsgi"), NONE("none"), POCO("poco"), PUMA("puma"),
        RESIN("resin"), SPRAY("spray"), STARMAN("starman"), THIN("thin"), TOMCAT("tomcat"), 
        TORNADO("tornado"), TORQBOX("torqbox"), TRINIDAD("trinidad"), UNDERTOW("undertow"),
        UNICORN("unicorn"), USERVER_TCP("userver_tcp"), UWSGI("uwsgi"), WARP("warp"), 
        WILDFLY("wildfly"), WOOF("woof"), XSP("xsp");
        
        private String frontEndServer;
        
        private FrontEndServerName(String frontEndServer)
        {
            this.frontEndServer = frontEndServer;
        }
        
        public String getFrontEndServer()
        {
            return this.frontEndServer;
        }
        
        public static FrontEndServerName getInstance(String frontEndServer)
        {
            for (FrontEndServerName t : values())
            {
                if (t.frontEndServer.equals(frontEndServer))
                    return t;
            }
            return null;
        }
    }
    
    public FrontEndServer(FrontEndServerName frontEndServer)
    {
        this(frontEndServer, null, false);
    }
    
    public FrontEndServer(FrontEndServerName frontEndServer, Integer id)
    {
        this(frontEndServer, id, true);
    }
    
    private FrontEndServer(FrontEndServerName frontEndServer, Integer id, boolean loaded)
    {
        super(id, loaded);
        this.frontEndServer = frontEndServer;
    }

    public FrontEndServerName getFrontEndServer()
    {
        return frontEndServer;
    }

    @Override
    public DomainObject clone()
    {
        FrontEndServer copy;
        try
        {
            copy = (FrontEndServer) super.clone();
            return copy;
        }
        catch (CloneNotSupportedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deeplyEquals(DomainObject obj)
    {
        if(this.equals(obj))
        {
            if(this.getFrontEndServer() != ((FrontEndServer) obj).getFrontEndServer())
                return false;
            if(this.isLoaded() != obj.isLoaded())
                return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FrontEndServer other = (FrontEndServer) obj;
        if (this.getId() != other.getId())
            return false;
        return true;
    }
    
    
}
