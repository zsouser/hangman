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
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String askUser(String question)
    {
       return showInputDialog(this, question);
       //return "";
    }
    
    
    public boolean confirmUser(String question) {
        return showConfirmDialog(this,question) == 0;
    } 
    
    public void tellUser(String message) {
        showMessageDialog(this, message);
    }
}
