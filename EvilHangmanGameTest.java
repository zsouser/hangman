import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EvilHangmanGameTest
{   
    List<String> words;
    public EvilHangmanGameTest() {
        words = new ArrayList<String>();
        words.add("ebb");
        words.add("ab");
        words.add("abc");
        words.add("and");
    }
    @Test
    public void answerTest() {
        EvilHangmanGame game = new EvilHangmanGame(words,2,2);
        // Assert whether the two letter word is returned
        assertEquals("ab",game.answer());
        assertNotNull(game.answer());
    }
    
    @Test
    public void guessTest() {
        EasyHangmanGame game = new EasyHangmanGame(words,3,5);
        // guess a letter
        int result = game.guess('a');
        // assert that the letter is guessed
        assert(game.guesses().contains((Character)'a'));
        // assert that only one letter was returned
        assertEquals(result,1);
        // check that the guesses count has not been decremented
        assertEquals(game.guessesLeft(),5);
        
        // guess another
        int secondResult = game.guess('u');
        // assert that the letter is guessed
        assert(game.guesses().contains((Character)'u'));
        // assert that there were 0 of the letter chosen
        assertEquals(secondResult,0);
        // assert that guess count has been decreased
        assertEquals(game.guessesLeft(),4);
    }
    
    @Test
    public void userWonTest() {
        EasyHangmanGame game = new EasyHangmanGame(words,2,3);
        game.guess('b');
        game.guess('a');
        assert(game.userWon());
    }
    
    @Test
    public void userLostTest() {
        EasyHangmanGame game = new EasyHangmanGame(words,3,2);
        game.guess('d');
        game.guess('f');
        assert(game.userLost());
    }
}
