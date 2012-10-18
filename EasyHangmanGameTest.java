import java.io.*;
import java.util.*;
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
public class EasyHangmanGameTest
{   
    List<String> words;
    public EasyHangmanGameTest() {
        words = new ArrayList<String>();
        words.add("ebb");
        words.add("card");
        words.add("alphabet");
        words.add("Mexico");
    }
    @Test
    public void answerTest() {
        EasyHangmanGame game = new EasyHangmanGame(words,3,5);
        assertEquals(game.answer(),"ebb");
    }
    
    @Test
    public void guessTest() {
        EasyHangmanGame game = new EasyHangmanGame(words,3,5);
        int result = game.guess('b');
        assert(game.guesses().contains((Character)'b'));
        assertEquals(result,2);
        assertEquals(game.guessesLeft(),5);
        int secondResult = game.guess('u');
        assert(game.guesses().contains((Character)'u'));
        assertEquals(secondResult,0);
        assertEquals(game.guessesLeft(),4);
    }
    
    @Test
    public void userWonTest() {
        EasyHangmanGame game = new EasyHangmanGame(words,3,3);
        game.guess('b');
        game.guess('e');
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
