import java.util.Scanner;
import java.util.List;

public class SpellCheckerDriver {

    public static void main ( String[] args ) {
        runSpellChecker();
    }

    private static void runSpellChecker() {
        Scanner input = new Scanner( System.in );
        SpellChecker sc = new SpellChecker("dictionary.txt");

        System.out.print( "Enter the name of file that you would like to spell check: " );
        String filename = input.nextLine();

        List<String> incorrectWords = sc.getIncorrectWords( filename );
        System.out.println( "The following will show the incorrect words found followed by any suggestions" );
        System.out.println("-------------------------------------------------------------------------------\n");
        for ( String word : incorrectWords ) {
            System.out.println( word + " : " + sc.getSuggestions( word ) );
        }
        System.out.println("");
        System.out.println( "Note: This is a basic spell checker and it doesn\'t account for grammatical errors and the dictionary used, may include words that aren\'t typically considered \"valid\" words");
        input.close();
    }
}
