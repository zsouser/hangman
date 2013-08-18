import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Driver class for the Hangman game
 * 
 * @author Zach Souser
 */
public class Hangman {
    /**
     * The main method, executes the hangman game and initialize the UI
     * @param  args   The arguments passed to the console
     */
    public static void main(String[] args) throws FileNotFoundException {
        HangmanUI ui = new HangmanGUI();
        playGame(ui);
    }
    
    /**
     * Load the dictionary of words
     * 
     * @param length the length of the words to be chosen
     * @throws FileNotFoundException
     */
    
    public static ArrayList<String> chooseWords(int length) throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<String>();
        Scanner sc = new Scanner(new File("words.txt"));
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            if (word.length() == length)
                words.add(word);
        }    
        return words;
    }
    
    /**
     * Play one iteration of the game
     * 
     * @param g The game state
     * @param u The UI state
     */
    public static void playGame(HangmanUI ui) {
    
        HangmanGame hg = initializeGame(ui);
        while (!hg.userWon() && !hg.userLost()) {
            showResults(hg,ui);
        }
        
        if (ui.confirmUser("Play again?")) playGame(ui);
        else ui.tellUser("Goodbye");
    }  
    
    /**
     * Initialize the game state
     * 
     * 
     * @param ui the user interface
     * @return the hangman game instance. Note: you can customize (evil/regular) 
     *          hangman games in this method
     */
    
    private static HangmanGame initializeGame(HangmanUI ui) {
        int length = 0;
        int guesses = 0;
        ArrayList<String> words = new ArrayList<String>();
        try {
            length = Integer.parseInt(ui.askUser("What length word do you want to use??"));
            guesses = Integer.parseInt(ui.askUser("How many wrong answers allowed?"));
            
            if (length < 2) {
                ui.tellUser("Choose a longer word. Must be at least 2 letters long.");
                return initializeGame(ui);
            }
        
            if (guesses < 2) {
                ui.tellUser("Come on, at least leave yourself 2 guesses!");
                return initializeGame(ui);
            }
            words = chooseWords(length);
            
        } catch (NumberFormatException e) {
            ui.tellUser("ERROR: Invalid number");
        } catch (FileNotFoundException e) {
            ui.tellUser("Invalid file");
        }
       
        return new EasyHangmanGame(words,length,guesses);
    }
    
    /** 
     * Show the results of the game state
     * 
     * @param g Game state
     * @param u UI state
     */
    
    public static void showResults(HangmanGame g, HangmanUI u) throws IllegalStateException {
        try {
            char guess = 0;
            do {
                guess = u.askUser("Your guess?").toLowerCase().charAt(0);
            } while (guess < 'A' || guess > 'z');
            
            int result = g.guess(guess);
            
            String message = "";
            if (result == 0) {
                message = message + "Sorry, there are no " + guess + "'s";
            } else if (result == 1) {
                message = message + "Yes, there is one " + guess;
            } else {
                message = message + "Yes, there are " + result + " " + guess + "'s";
            }
            if (g.userWon()) {
                message = message + "\nThe word was " + g.answer()+"\nYou won! Congratulations.";
            }
            else if (g.userLost()) {
                message = message + "\nThe word was " + g.answer() + "\nSorry, you lose.";
            }
            else {
                message = message + "\n\nguesses left : " + g.guessesLeft() + "\n" +
                        "guessed : " + g.guesses() + "\n" +
                        "current : " + g.pattern();
            }
            u.tellUser(message);
        } catch (IllegalStateException e) {
            u.tellUser("Game is over");
        } catch (IllegalArgumentException e) {
            u.tellUser("Already guessed that.");
            showResults(g,u);
        } catch (IndexOutOfBoundsException e) {
            if (u.confirmUser("Invalid input. Keep playing?"))
                showResults(g,u);
        }
    }
}
