import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;
/**
 * The EvilHangmanGame class is an implementation of the HangmanGame interface
 * that elusively does not select the chosen word unless the latest possible time.
 * This class, however, does attempt to deceive the user by providing an accurate pattern
 * for the guessed characters.
 * 
 * @author Zach Souser
 */
public class EvilHangmanGame implements HangmanGame
{
    // The number of guesses left
    private int guessesLeft;
    // The set of letters guessed
    private SortedSet<Character> guesses;
    // The working collection of words
    private List<String> words;
    // The length of the target word
    private int length;
    // The working pattern to be provided to the user
    private String pattern;
    
    
    /**
     * Constructor for objects of class EvilHangmanGame
     * @param words A List of words to use
     * @param length The length of the word to be chosen
     * @param guesses The number of guesses allowed
     */
    
    public EvilHangmanGame(List<String> words, int length, int guesses)
    {
        if (words == null) System.out.println("Words is null");
        this.words = words;
        
        this.guesses = new TreeSet<Character>();
        this.guessesLeft = guesses;
        this.length = length;
        this.pattern = "";
        for (int i = 0; i < length; i++) {
            this.pattern += "- ";
        }
    }
    
    /**
     * Randomly selects an answer from the list of available words
     * @return The randomly selected answer
     */
    
    public String answer() {
        Random r = new Random();
        System.out.println(this.words);
        return this.words.get(r.nextInt(this.words.size()));
       
    }
    
    /**
     * Accessor for the list of guesses
     * @return The list of guesses
     */
    
    public SortedSet<Character> guesses() {
        return this.guesses;
    }
    
    /**
     * Accessor for the number of guesses left in the game
     * @return The number of guesses left
     */
    
    public int guessesLeft() {
        return this.guessesLeft;
    }
    
    /**
     * Accessor method for the pattern used by the game
     * @return The working pattern
     */
    
    public String pattern() {
        return this.pattern;
    }
    
    /**
     * Evaluates if the user has lost the game,
     * 
     * @return Whether the user lost the game
     */
    public boolean userLost() {
        return guessesLeft == 0 || words == null || words.isEmpty();
        
    }
    
    /**
     * Evaluates if the user has won the game,
     * 
     * @return Whether the user won the game
     */
    public boolean userWon() {
        for (int i = 0; i < this.pattern.length(); i+=2) {
            if (this.pattern.charAt(i) == '-') return false;
        }
        return true;
    }
    
    /**
     * Guess a character. Sorts the dictionary based on 
     * the occurrence of the guessed character. Then the largest
     * set of words matching the guessed character replaces
     * the working dictionary and the corresponding pattern applies
     * 
     * @param guess the guessed character
     * @return the number of occurrences of the guessed character in the goal word
     */
    public int guess(char guess) throws IllegalStateException, IllegalArgumentException {
        if (this.guessesLeft == 0) throw new IllegalStateException(); 
        if (this.guesses.contains(guess)) throw new IllegalArgumentException();
        
        this.guesses.add(guess);
        TreeMap<String, ArrayList<String>> patterns = new TreeMap<String,ArrayList<String>>();
        
        for (String w : this.words) {
            int count = 0;
            String pattern = "";
            
            for (int i = 0; i < w.length(); i++) {
                char patternChar = this.pattern.charAt(2*i);
                if (patternChar == '-') {
                    if (w.charAt(i) == guess) {
                        pattern += guess + " ";
                        count++;
                    } else {
                        pattern += "- ";
                    }
                } else if (w.charAt(i) == patternChar) {
                    pattern += patternChar + " ";
                } else {
                    count = 0;
                }
            }
            //System.out.println(patterns);
            if (count > 0) {
                if (patterns.containsKey(pattern)) {
                    ArrayList<String> list = patterns.get(pattern);
                    list.add(w);
                    patterns.put(pattern,list);
                } else {
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(w);
                    patterns.put(pattern, list);
                }
            } 
        }
        
        int max = 0;
        String pattern = "";
        
        for (String p : patterns.keySet()) {
            ArrayList<String> list = patterns.get(p);
            int size = list.size();
            if (!patterns.get(p).isEmpty() && size > max) {
                max = size;
                pattern = p;
            }
        }
        
        int n = 0;
        
        for (int i = 0; i < pattern.length(); i+=2) {
            if (pattern.charAt(i) == guess) {
                n++;
            }
        }
        
        if (pattern != "") {
            this.pattern = pattern;
            this.words = patterns.get(pattern);
        }
        if (n == 0) guessesLeft--;
        return n;
       
    }
}
