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
        System.out.println("Welcome to the hangman guessing game");
        HangmanUI ui = new HangmanGUI();
        try {
            int length = Integer.parseInt(ui.askUser("What length word do you want to use??"));
            int guesses = Integer.parseInt(ui.askUser("How many wrong answers allowed?"));
            if (length < 2) {
                ui.tellUser("Choose a longer word. Must be at least 2 letters long.");
                main(args);
            } 
            if (guesses < 2) {
                ui.tellUser("Come on, at least leave yourself 2 guesses!");
                main(args);
            }
            ArrayList<String> words = chooseWords(length);
            
            HangmanGame hg = new EvilHangmanGame(words,length,guesses);
            
            while (!hg.userWon() && !hg.userLost()) {
                showResults(hg,ui);
                playGame(hg,ui);
            }
            
            if (ui.confirmUser("Play again?")) main(args);
            else ui.tellUser("Goodbye");
            
        } catch (NumberFormatException e) {
            ui.tellUser("ERROR: Invalid number");
            main(args);
        }
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
            words.add(sc.nextLine());
        }    
        return words;
    }
    
    /**
     * Play one iteration of the game
     * 
     * @param g The game state
     * @param u The UI state
     */
    public static void playGame(HangmanGame g, HangmanUI u) {
        try {
            char guess = u.askUser("Your guess?").toLowerCase().charAt(0);
            try {
                int result = g.guess(guess);
                if (result == 0) {
                    u.tellUser("Sorry, there are no " + guess + "'s");
                } else if (result == 1) {
                    u.tellUser("Yes, there is one " + guess);
                } else {
                    u.tellUser("Yes, there are " + result + " " + guess + "'s");
                }
                if (g.userWon()) {
                    u.tellUser("The word was " + g.answer());
                    u.tellUser("You won! Congratulations.");
                }
                if (g.userLost()) {
                    u.tellUser("The word was " + g.answer());
                    u.tellUser("Sorry, you lose.");
                }
            } catch (IllegalStateException e) {
                System.out.println("Game is over");
            } catch (IllegalArgumentException e) {
                System.out.println("Already guessed that.");
            }
        } catch (IndexOutOfBoundsException e) {
            u.tellUser("Invalid input");
            playGame(g,u);
        }
    }   
    
    /** 
     * Show the results of the game state
     * 
     * @param g Game state
     * @param u UI state
     */
    
    public static void showResults(HangmanGame g, HangmanUI u) {
        u.tellUser("guesses left : " + g.guessesLeft() + "\n" +
                    "guessed : " + g.guesses() + "\n" +
                    "current : " + g.pattern());
    }
}
