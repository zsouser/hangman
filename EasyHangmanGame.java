import java.util.*;
import java.io.*;
import java.lang.Math.*;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EasyHangmanGame implements HangmanGame
{
    // instance variables - replace the example below with your own
    private int guessesLeft;
    private String word;
    private SortedSet<Character> guesses;
    private List<String> words;
    /**
     * Constructor for objects of class Game
     */
    public EasyHangmanGame(List<String> words, int length, int guesses)
    {
        this.words = words;
        this.word = this.chooseWord(length);
        this.guesses = new TreeSet<Character>();
        this.guessesLeft = guesses;
    }
    
    public String chooseWord(int length) {
        Random r = new Random();
        String next = "";
        while (next.length() != length) {
            next = this.words.get(r.nextInt(words.size()));
        }
        return next;
    }
    
    public String answer() {
        return this.word;
    }
    
    public SortedSet<Character> guesses() {
        return this.guesses;
    }
    
    public int guessesLeft()
    {
        return this.guessesLeft;
    }
    
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
    
    public String pattern() {
        String str = "";
        for (int i = 0; i < this.word.length(); i++) {
            if (this.guesses.contains(this.word.charAt(i))) str += this.word.charAt(i) + " ";
            else str += " - ";
        }
        return str;
    }
    
    public boolean userLost() {
        return guessesLeft == 0;
        
    }
    
    public boolean userWon() {
        for (int i = 0; i < this.word.length(); i++) {
            if (!guesses.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
