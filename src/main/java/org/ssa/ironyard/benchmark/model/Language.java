package org.ssa.ironyard.benchmark.model;

public class Language extends AbstractDomainObject implements DomainObject
{
    private final LanguageName name;

    public enum LanguageName
    {
        JAVA("Java"), C("C"), C_SHARP("C#"), C_PLUS_PLUS("C++"), 
        CLOJURE("Clojure"), CRYSTAL("Crystal"), D("D"), DART("Dart"), 
        ELIXIR("Elixir"), ERLANG("Erlang"), GO("Go"), GROOVY("Groovy"), 
        HASKELL("Haskell"), JAVASCRIPT("JavaScript"), LUA("Lua"), 
        NIM("Nim"), PERL("Perl"), PHP("PHP"), PHP5("PHP5"), 
        PYTHON("Python"), RACKET("Racket"), RUBY("Ruby"), RUST("rust"), 
        SCALA("Scala"), UR("Ur");

        private String language;

        private LanguageName(String language)
        {
            this.language = language;
        }

        public String getName()
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

    public Language()
    {
        this(null, null, false);
    }

    public Language(LanguageName language)
    {
        this(language, null, false);
    }

    public Language(LanguageName language, int id)
    {
        this(language, id, true);
    }

    private Language(LanguageName name, Integer id, boolean loaded)
    {
        super(id, loaded);
        this.name = name;
    }

    public LanguageName getLanguage()
    {
        return name;
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
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deeplyEquals(DomainObject obj)
    {
        if (this.equals(obj))
        {
            
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
        if (this.getId() == null)
        {
            if (other.getId() != null)
                return false;
        }
        else if (!this.getId().equals(other.getId()))
        {
            return false;
        }
        
        if (this.getLanguage() == null)
        {
            if (other.getLanguage() != null)
                return false;
        }
        else if (this.getLanguage() != ((Language) obj).getLanguage())
            return false;
        return true;
    }

}
