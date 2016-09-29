package org.ssa.ironyard.benchmark.service;

import java.util.List;

import org.ssa.ironyard.benchmark.dao.DAOLanguageImpl;
import org.ssa.ironyard.benchmark.model.Language;

public class LanguageServiceImpl implements LanguageService
{
    DAOLanguageImpl languageDAO;

    @Override
    public List<Language> getAllLanguages()
    {
        return languageDAO.read();
    }

    @Override
    public List<Language> getLanguagesByName(Language language)
    {
        return languageDAO.readByLanguage(language);
    }

    @Override
    public Language getLanguageByID(int languageID)
    {
        return languageDAO.read(languageID);
    }

    @Override
    public Language addLanguage(Language language)
    {
        return languageDAO.insert(language);
    }

    @Override
    public boolean deleteLanguage(int languageID)
    {
        return languageDAO.delete(languageID);
    }

}
