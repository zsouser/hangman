

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HangmanGUITest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HangmanTUITest
{
    
    @Test
    public void askUserTest() {
        HangmanTUI tui = new HangmanTUI();
        assertEquals("type asdf",tui.askUser("question"));
    }
    
    @Test
    public void confirmUserTest() {
        HangmanTUI tui = new HangmanTUI();
        assert(tui.confirmUser("Say yes"));
        assertFalse(tui.confirmUser("Say no"));
    }
}
