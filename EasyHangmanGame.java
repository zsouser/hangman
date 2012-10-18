import java.util.TreeSet;
import java.util.SortedSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
/**
 * Basic hangman game. The game chooses a word, allows the user to guess
 * letters in the chosen word.
 * 
 * @author Zach Souser
 */
public class EasyHangmanGame implements HangmanGame
{
    // The number of guesses left
    private int guessesLeft;
    // The goal word
    private String word;
    // The set of guessed letters
    private SortedSet<Character> guesses;
    // The words from which the answer is chosen
    private List<String> words;
    /**
     * Constructor for objects of class EasyHangmanGame
     * 
     * @param words The list of words
     * @param length The length of the goal word
     * @param guesses The number of guesses allowed
     */
    public EasyHangmanGame(List<String> words, int length, int guesses)
    {
        this.words = words;
        this.word = this.chooseWord(length);
        this.guesses = new TreeSet<Character>();
        this.guessesLeft = guesses;
    }
    
    /**
     * Choose the word from within the constructor
     * 
     * @param length The length of the word to be chosen
     * @return The first randomly chosen word of the given length
     */
    private String chooseWord(int length) {
        Random r = new Random();
        String next = "";
        while (next.length() != length) {
            next = this.words.get(r.nextInt(words.size()));
        }
        return next;
    }
    
    /**
     * Accessor for the answer
     * 
     * @return The goal word
     */
    
    public String answer() {
        return this.word;
    }
    
    /**
     * Accessor for the set of guesses
     * 
     * @return the guessed letters
     */
    
    public SortedSet<Character> guesses() {
        return this.guesses;
    }
    
    /**
     * Accessor for the number of guesses left
     * 
     * @return the number of guesses left
     */
    public int guessesLeft() {
        return this.guessesLeft;
    }
    
    /**
     * Guess a character. Update the number of guesses, and add
     * the guess to the set of guesses
     * 
     * @return the number of occurrences of the guessed letter in the word
     */
    
    public int guess(char guess) throws IllegalStateException, IllegalArgumentException {
        if (this.word.isEmpty() || this.word == null || this.guessesLeft == 0) throw new IllegalStateException(this.word); 
        if (this.guesses.contains(guess)) throw new IllegalArgumentException();
        
        this.guesses.add(guess);
        int count = 0;
        
        for (int i = 0; i < this.word.length(); i++) {
            if (word.charAt(i) == guess) count++; 
        }
        
        if (count == 0) {
            this.guessesLeft--;
        }
        
        return count;
    }
    
    /**
     * Returns the pattern to be shown to the user. If a letter is guessed,
     * it is shown. If not, a dash is shown.
     * 
     * @return The pattern string for the game state
     */
    
    public String pattern() {
        String str = "";
        for (int i = 0; i < this.word.length(); i++) {
            if (this.guesses.contains(this.word.charAt(i))) str += this.word.charAt(i) + " ";
            else str += " - ";
        }
        return str;
    }
    
    /**
     * Asserts whether the user has lost
     * 
     * @return Whether the user has lost
     */
    
    public boolean userLost() {
        return guessesLeft == 0;
        
    }
    
    /**
     * Asserts whether the user has won
     * 
     * @return Whether the user has won
     */
    
    public boolean userWon() {
        for (int i = 0; i < this.word.length(); i++) {
            if (!guesses.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
