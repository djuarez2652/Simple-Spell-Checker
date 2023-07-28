import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;

public class SpellChecker implements SpellCheckerInterface {
    
    private BufferedReader br;
    private HashSet<String> dictionary;


    public SpellChecker ( String filename ) {

        
        dictionary = new HashSet<String>();

        try {
            br = new BufferedReader( new FileReader( filename ) );
        }
        catch ( Exception e ) {
            throw new RuntimeException( "Could not find the dictionary file with name: " + filename );
        }

        try {
            String line;

            while ( ( line = br.readLine() ) != null ) {
                line = line.toLowerCase();
                line = line.replaceAll( "\\p{Punct}", "" );

                dictionary.add( line );
            }
            br.close();
        }
        catch ( Exception e ) {
            throw new RuntimeException( "There was a problem processing the dictionary: " + e.toString() );
        }
    }

    public List<String> getIncorrectWords ( String filename ) {
        ArrayList<String> incorrectWords = new ArrayList<String>();

        try {
            br = new BufferedReader( new FileReader( filename ) );
        }
        catch ( Exception e ) {
            throw new RuntimeException( "Could not find file with name: " + filename );
        }

        try {
            String line;

            while ( ( line = br.readLine() ) != null ) {

                if ( line.equals( "" ) )
                    continue;
                
                String[] words = line.split( "\\s+" );
                for ( String word : words ) {
                    word = word.toLowerCase();
                    word = word.replaceAll( "\\p{Punct}||\\p{Space}", "" );

                    if ( !dictionary.contains( word ) )
                        incorrectWords.add( word );
                }

            }
            br.close();
        }
        catch ( Exception e ) {
            throw new RuntimeException( "There was a problem processing the file: " + e.toString() );
        }

        return incorrectWords;
    }

    public Set<String> getSuggestions ( String word ) {
        Set<String> suggestions = new HashSet<String>();

        StringBuilder wordArray = new StringBuilder( word );
        int length = word.length();

        // Checks if replacing characters makes a valid word
        for ( int i = 0; i < length; i++ ) {
            for ( char ch = 'a'; ch <= 'z'; ch++ ) {
                char prevChar = wordArray.charAt( i );

                wordArray.setCharAt( i, ch );

                if ( dictionary.contains( wordArray.toString() ) )
                    suggestions.add( wordArray.toString() );
                
                wordArray.setCharAt( i, prevChar );
            }
        }

        // Checks if inserting a character makes a valid word
        for ( int i = 0; i < length + 1; i++ ) {
            for ( char ch = 'a'; ch <= 'z'; ch++ ) {
                wordArray.insert( i, ch );


                if ( dictionary.contains( wordArray.toString() ) ){
                    suggestions.add( wordArray.toString() );
                }
                
                wordArray.deleteCharAt( i );
            }
        }

        // Checks if deleting a character makes a valid word
        for ( int i = 0; i < length; i++ ) {
            char deletedChar = wordArray.charAt( i );

            wordArray.deleteCharAt( i );

            if ( dictionary.contains( wordArray.toString() ) )
                suggestions.add( wordArray.toString() );
            
            wordArray.insert( i, deletedChar );
        }

        // Checks if swapping adjacent characters makes valid word
        for ( int i = 0; i < length - 1; i++ ) {
            char firstChar = wordArray.charAt( i );
            char secondChar = wordArray.charAt( i + 1 );

            wordArray.setCharAt( i + 1 , firstChar );
            wordArray.setCharAt( i , secondChar );

            if ( dictionary.contains( wordArray.toString() ) )
                suggestions.add( wordArray.toString() );

            wordArray.setCharAt( i, firstChar );
            wordArray.setCharAt( i + 1, secondChar );
        }

        return suggestions;
    }
}