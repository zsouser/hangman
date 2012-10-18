import java.util.Scanner;

/**
 * Write a description of class HangmanTUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HangmanTUI implements HangmanUI
{
    // The scanner for the console
    Scanner console;

    /**
     * Constructor for objects of class HangmanTUI
     * Initializes the text UI
     */
    public HangmanTUI()
    {
        console = new Scanner(System.in);
    }

    /**
     * Ask the user a question
     * 
     * @param  question   The question shown to the user
     * @return     The string provided by the user in response
     */
    public String askUser(String question) {
        System.out.println(question);
        return console.nextLine();
    }
    
    /** 
     * Tell the user something
     * 
     * @param message The message to be passed
     */
    
    public void tellUser(String message) {
        System.out.println(message);
    }
    
    /** 
     * Prompt the user, asking them to confirm yes or no
     * 
     * @param prompt The prompt to be shown to the user
     * @return the user's response
     */
    
    public boolean confirmUser(String prompt) {
        tellUser(prompt);
        char answer = askUser("y/n?").charAt(0);
        if (answer == 'y' || answer == 'Y') return true;
        return false;
    }
        
}
