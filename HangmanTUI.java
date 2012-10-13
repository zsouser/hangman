import java.io.*;
import java.util.*;

/**
 * Write a description of class HangmanTUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HangmanTUI implements HangmanUI
{
    // instance variables - replace the example below with your own
    Scanner console;

    /**
     * Constructor for objects of class HangmanTUI
     */
    public HangmanTUI()
    {
        console = new Scanner(System.in);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String askUser(String question) {
        System.out.println(question);
        return console.nextLine();
    }
    
    public void tellUser(String message) {
        System.out.println(message);
    }
    
    public boolean confirmUser(String prompt) {
        tellUser(prompt);
        char answer = askUser("y/n?").charAt(0);
        if (answer == 'y' || answer == 'Y') return true;
        return false;
    }
        
}
