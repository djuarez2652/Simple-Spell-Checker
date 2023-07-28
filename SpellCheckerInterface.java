import java.util.List;
import java.util.Set;

public interface SpellCheckerInterface {
    public List<String> getIncorrectWords( String filename );
    public Set<String> getSuggestions ( String word );
}