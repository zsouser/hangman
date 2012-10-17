import java.util.*;
import java.io.*;
import java.lang.Math.*;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EvilHangmanGame implements HangmanGame
{
    // instance variables - replace the example below with your own
    private int guessesLeft;
    private SortedSet<Character> guesses;
    private List<String> words;
    private int length;
    private String pattern;
    /**
     * Constructor for objects of class Game
     */
    public EvilHangmanGame(List<String> words, int length, int guesses)
    {
        this.words = new ArrayList<String>();
        
        for (String w : words) {
            if (w.length() == length) this.words.add(w);
        }
        
        this.guesses = new TreeSet<Character>();
        this.guessesLeft = guesses;
        this.length = length;
        this.pattern = "";
        for (int i = 0; i < length; i++) {
            this.pattern += "- ";
        }
    }
    
    public String answer() {
        Random r = new Random();
        return this.words.get(r.nextInt(words.size()));
    }
    
    public SortedSet<Character> guesses() {
        return this.guesses;
    }
    
    public int guessesLeft() {
        return this.guessesLeft;
    }
    
    public String pattern() {
        return this.pattern;
    }
    
    public boolean userLost() {
        return guessesLeft == 0;
        
    }
    
    public boolean userWon() {
        for (int i = 0; i < this.pattern.length(); i+=2) {
            if (this.pattern.charAt(i) == '-') return false;
        }
        return true;
    }
    
    public int guess(char guess) throws IllegalStateException, IllegalArgumentException {
        if (this.guessesLeft == 0) throw new IllegalStateException(); 
        if (this.guesses.contains(guess)) throw new IllegalArgumentException();
       
        this.guesses.add(guess);
        TreeMap<String, ArrayList<String>> patterns = new TreeMap<String,ArrayList<String>>();
        if (this.words == null) return 0;
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
            if (size > max) {
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
        
        this.pattern = pattern;
        this.words = patterns.get(pattern);
        System.out.println(n + " " + pattern);
        return n;
       
    }
}
