package org.ssa.ironyard.benchmark.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Benchmark extends AbstractDomainObject implements DomainObject
{
    private final String name;
    private Language language;
    private FrontEndServer frontEndServer;
    private Map<Threads, BigInteger> performance;
    private BigInteger errors;

    public enum Threads
    {
        _8("8_threads"), _16("16_threads"), _32("32_threads"), _64("64_threads"), _128("128_threads"), _256("256_threads");

        private String numberThreads;

        private Threads(String numberThreads)
        {
            this.numberThreads = numberThreads;
        }

        public String getThreads()
        {
            return numberThreads;
        }

        public static Threads getInstance(String numberThreads)
        {
            for (Threads t : values())
            {
                if (t.numberThreads.equals(numberThreads))
                    return t;

            }
            return null;
        }
    }

    public Benchmark(String name)
    {
        this(name, null, false);
    }

    public Benchmark(String name, int id)
    {
        this(name, id, true);
    }

    private Benchmark(String name, Integer id, boolean loaded)
    {
        super(id, loaded);
        this.name = name;
        this.frontEndServer = null;
        this.language = null;
        this.performance = new HashMap<>();
    }

    public String getName()
    {
        return name;
    }

    public Language getLanguage()
    {
        return language;
    }

    public void setLanguage(Language language)
    {
        this.language = language;
    }

    public FrontEndServer getFrontEndServer()
    {
        return frontEndServer;
    }

    public void setFrontEndServer(FrontEndServer frontEndServer)
    {
        this.frontEndServer = frontEndServer;
    }

    public Map<Threads, BigInteger> getPerformance()
    {
        return performance;
    }

    public void setPerformance(Map<Threads, BigInteger> performance)
    {
        this.performance = performance;
    }

    public BigInteger setPerformance(Threads threads, BigInteger performance)
    {
        return this.performance.put(threads, performance);
    }

    public BigInteger getErrors()
    {
        return errors;
    }

    public void setErrors(BigInteger errors)
    {
        this.errors = errors;
    }

    @Override
    public Benchmark clone()
    {
        Benchmark copy;
        try
        {
            copy = (Benchmark) super.clone();
            copy.setFrontEndServer(this.getFrontEndServer());
            copy.setLanguage(this.getLanguage());
            copy.setPerformance(this.getPerformance());
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
        if (this.equals(obj))
        {   
            Benchmark other = (Benchmark) obj;
            if(this.isLoaded() != other.isLoaded())
                return false;
            
            if(this.getName() == null)
            {
                if(other.getName() != null)
                    return false;
            }       
            else if (!this.getName().equals(other.getName()))
                return false;
            
            if(this.getFrontEndServer() == null)
            {
                if(other.getFrontEndServer() != null)
                    return false;
            }
            else if (!this.getFrontEndServer().deeplyEquals(other.getFrontEndServer()))
                return false;
            
            if(this.getLanguage() == null)
            {
                if(other.getLanguage() != null)
                    return false;
            }
            else if (!this.getLanguage().deeplyEquals(other.getLanguage()))
                return false;
            
            if (!this.getPerformance().equals(other.getPerformance()))
                return false;
            
            if(this.getErrors() == null)
            {
                if(other.getErrors() != null)
                    return false;
            }
            else if (!this.getErrors().equals(other.getErrors()))
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
        Benchmark other = (Benchmark) obj;
        if(this.getId() == null)
        {
            if(other.getId() != null)
                return false;
        }
        else if (!this.getId().equals(other.getId()))
            return false;
        return true;
    }

}
