package org.ssa.ironyard.benchmark.model;

public interface DomainObject extends Cloneable
{
    Integer getId();
    
    DomainObject clone();

    boolean deeplyEquals(DomainObject obj);
    
    boolean isLoaded();
}
