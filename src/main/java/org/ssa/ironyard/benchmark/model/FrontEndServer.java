package org.ssa.ironyard.benchmark.model;

public class FrontEndServer extends AbstractDomainObject implements DomainObject
{
    private final FrontEndServerName frontEndServer;
    
    public enum FrontEndServerName
    {
        AKKA("Akka"), APACHE("Apache"), BLAZE("blaze"), CHERRYPY("CherryPy"), COWBOY("Cowboy"),
        CPOLL("CPoll"), D("D"), DART("Dart"), ELLI("Elli"), GEMINI("Gemini"), GO("Go"), 
        GRIZZLY("Grizzly"), GUNICORN("Gunicorn"), HPHP("HPHP"), HTTP_KIT("http-kit"), HYPER("hyper"),
        HYPNOTOAD("Hypnotoad"), IMMUTANT("Immutant"), JETTY("Jetty"), LWAN("Lwan"), MEINHELD("Meinheld"),
        MONKEY("Monkey"), NETTY("Netty"), NGINX("nginx"), NODEJS("NodeJS"), ONION("Onion"), PUMA("Puma"),
        RACKET("Racket"), RESIN("Resin"), SNAP("Snap"), SPRAY("Spray"), STARMAN("Starman"), THIN("Thin"),
        TOMCAT("Tomcat"), TORNADO("Tornado"), TORQBOX("TorqBox"), TRINIDAD("Trinidad"), TWISTED("Twisted"),
        UNDERTOW("Undertow"), UNDERTOW_EDGE("Undertow Edge"), UNICORN("Unicorn"), URWEB("Ur/Web"),
        VERTX("Vertx"), WARP("Warp"), WEB2PY("web2Py"), WILDFLY("Wildfly"), WT("Wt");
          
        private String frontEndServer;
        
        private FrontEndServerName(String frontEndServer)
        {
            this.frontEndServer = frontEndServer;
        }
        
        public String getName()
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
    public FrontEndServer clone()
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
        result = prime * result + ((this.getFrontEndServer() == null) ? 0 : this.getFrontEndServer().hashCode());
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
        if(this.getId() == null)
        {
            if(other.getId() != null)
                return false;
        }
        else if (!this.getId().equals(other.getId()))
            return false;
        
        if(this.getFrontEndServer() == null)
        {
            if(other.getFrontEndServer() != null)
                return false;
        }
        else if(this.getFrontEndServer() != ((FrontEndServer) obj).getFrontEndServer())
            return false;
        return true;
    }
    
    
}
