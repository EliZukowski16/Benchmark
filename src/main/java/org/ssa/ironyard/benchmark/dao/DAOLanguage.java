package org.ssa.ironyard.benchmark.dao;

import java.util.List;

import org.ssa.ironyard.benchmark.model.Language;

public interface DAOLanguage extends DAO<Language>
{
    public List<Language> read();
    public List<Language> readByLanguage(Language language);
}
