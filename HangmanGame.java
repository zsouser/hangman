import java.util.SortedSet;
/**
 * Write a description of interface HangmanGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface HangmanGame
{
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the result produced by sampleMethod 
     */
    public String answer();
    public SortedSet<Character> guesses();
    public int guessesLeft();
    public int guess(char guess);
    public String pattern();
    public boolean userLost();
    public boolean userWon();
}
