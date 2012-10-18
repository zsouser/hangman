import javax.swing.*;

/**
 * Write a description of class HangmanGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HangmanGUI extends JOptionPane implements HangmanUI
{
    // instance variables - replace the example below with your own
    private int x;
    
    /**
     * Constructor for objects of class HangmanGUI
     */
    public HangmanGUI()
    {
        super();
        setSize(400,100);
    }

    /**
     * Ask the user a question
     * 
     * @param  question the question
     * @return the string submitted by the user 
     */
    public String askUser(String question)
    {
       return showInputDialog(this, question);
    }
    
    /**
     * Confirm something from the user
     * 
     * @param question The question asked
     * @return the true/false yes/no response
     */
    public boolean confirmUser(String question) {
        return showConfirmDialog(this,question) == 0;
    } 
    
    /**
     * Tell the user something
     * 
     * @param message The message to be passed to the user
     */
    
    public void tellUser(String message) {
        showMessageDialog(this, message);
    }
}
