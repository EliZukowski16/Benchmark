package org.ssa.ironyard.benchmark.service;

import java.util.List;

import org.ssa.ironyard.benchmark.model.Language;

public interface LanguageService
{
    public List<Language> getAllLanguages();
    public List<Language> getLanguagesByName(Language language);
    public Language getLanguageByID(int languageID);
    public Language addLanguage(Language language);
    public boolean deleteLanguage(int languageID);
}
