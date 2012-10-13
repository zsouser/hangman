

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
public class HangmanGUITest
{
    
    @Test
    public void askUserTest() {
        HangmanGUI gui = new HangmanGUI();
        assertEquals("asdf",gui.askUser("Type asdf"));
    }
    
    @Test
    public void confirmUserTest() {
        HangmanGUI gui = new HangmanGUI();
        assert(gui.confirmUser("Say yes"));
        assertFalse(gui.confirmUser("Say no"));
    }
}
