package org.ssa.ironyard.benchmark.model;

public class Language extends AbstractDomainObject implements DomainObject
{
    final LanguageName language;

    public enum LanguageName
    {
        JAVA("java"), C("c"), C_SHARP("c-sharp"), C_PLUS_PLUS("c-plus-plus"), CLOJURE("clojure"), CRYSTAL("crystal"), D(
                "d"), DART("dart"), ELIXIR("elixir"), ERLANG("erlang"), GO("go"), GROOVY("groovy"), HASKELL(
                        "haskell"), JAVASCRIPT("javascript"), LUA("lua"), NIM("nim"), PERL("perl"), PHP("php"), PHP5(
                                "php5"), PYTHON("python"), RACKET(
                                        "racket"), RUBY("ruby"), RUST("rust"), SCALA("scala"), UR("ur");

        private String language;

        private LanguageName(String language)
        {
            this.language = language;
        }

        public String getLanguage()
        {
            return this.language;
        }

        public static LanguageName getInstance(String language)
        {
            for (LanguageName t : values())
            {
                if (t.language.equals(language))
                    return t;
            }
            return null;
        }
    }

    public Language(LanguageName language)
    {
        this(language, null, false);
    }

    public Language(LanguageName language, int id)
    {
        this(language, id, true);
    }

    private Language(LanguageName language, Integer id, boolean loaded)
    {
        super(id, loaded);
        this.language = language;
    }

    public LanguageName getLanguage()
    {
        return language;
    }

    @Override
    public Language clone()
    {
        Language copy;
        try
        {
            copy = (Language) super.clone();
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
            if (this.getLanguage() != ((Language) obj).getLanguage())
                return false;
            if (this.isLoaded() != obj.isLoaded())
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
        Language other = (Language) obj;
        if (this.getId() != other.getId())
            return false;
        return true;
    }

}
