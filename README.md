##Simple Spell Checker

This simple Spell Checker checks for misspelled words in a file inputted by the user. It does this by checking whether it exists inside the "*dictionary.txt*" which are filtered words taken from [this dictionary](https://www.mit.edu/~ecprice/wordlist.10000). For every misspelled word found, the program prints out a set of possible correct words, if any are found.

>Note: The dictionary file used isn't an exhaustive list of valid words & the spell checker doesn't check for grammatical errors ( e.g. the word *like* will not be marked wrong even if it's used incorrectly - "He *like* to play soccer" )

###How it works:

When instantiated, the spell checker reads through the "*dictionary.txt*" file, removes puncuation, and it adds the words to a `HashSet`. Next, the program asks the user to input the name of the file that they wish to process. The progam reads the file, if found, and it begins parsing through, removing any punctuation or spaces. Next, it checks if the word is in the `HashSet`: if it is not found, the program adds it to an `ArrayList` of Strings. Once it parses through all the words it returns the `ArrayList`. Then, a for loop iterates through the `ArrayList` returned and prints out the value, and it calls the `getSuggestions` function for the current incorrect word and prints out what it returns. Inside the `getSuggestions` function, the word is turned into a `StringBuilder` which is goes through four different manipulations to find a correct word which, if found, are added to a `Set` that is returned at the end.
The first manipulation goes through each character and uses another for loop to iterate from 'a' through 'z' to replace the current character with.
The second manipulation uses a for loop to iterate from 'a' to 'z' and inserts that character in each possible spot of the word.
The third manipulation iterates through the characters and deletes it to check if the manipulation creates a valid word.
The fourth manipulation swaps each adjacent character to check if it creates a valid word.