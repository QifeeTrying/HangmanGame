import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameProcess {
    private WordManager wordManager;
    private String selectedWord;
    private List<Character> guessedLetters;
    private int incorrectGuesses;
    private static final int MAX_INCORRECT_GUESSES = 6; // Number of allowed incorrect guesses
    private static final char HIDDEN_CHAR = '_'; // Placeholder for hidden letters

    public GameProcess() {
        wordManager = new WordManager(); // Initialize WordManager
        guessedLetters = new ArrayList<>();
        incorrectGuesses = 0;
    }

    // Start the game
    public void startGame() {
        // Select a random word
        selectedWord = wordManager.getRandomWord();

        if (selectedWord == null) {
            System.out.println("No words available. Exiting...");
            return;
        }

        System.out.println("Welcome to Hangman!");
        Scanner scanner = new Scanner(System.in);

        // Game loop
        while (incorrectGuesses < MAX_INCORRECT_GUESSES) {
            System.out.println("\nCurrent word: " + getCurrentWordState());
            System.out.println("Incorrect guesses left: " + (MAX_INCORRECT_GUESSES - incorrectGuesses));
            System.out.print("Enter a letter to guess: ");
            String input = scanner.nextLine().toLowerCase();

            // Validate input
            if (input.length() != 1 || !input.matches("[a-z]")) {
                System.out.println("Please enter a single letter (a-z).");
                continue;
            }

            char guess = input.charAt(0);

            // Check if letter has already been guessed
            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter.");
                continue;
            }

            // Add guessed letter to the list
            guessedLetters.add(guess);

            // Check if the guess is correct
            if (selectedWord.indexOf(guess) >= 0) {
                System.out.println("Good guess!");
                if (isWordComplete()) {
                    System.out.println("Congratulations, you guessed the word: " + selectedWord);
                    break;
                }
            } else {
                incorrectGuesses++;
                System.out.println("Incorrect guess! You have " + (MAX_INCORRECT_GUESSES - incorrectGuesses) + " attempts left.");
            }
        }

        if (incorrectGuesses == MAX_INCORRECT_GUESSES) {
            System.out.println("\nGame over! You ran out of attempts.");
            System.out.println("The word was: " + selectedWord);
        }
    }

    // Get the current state of the word with hidden letters
    private String getCurrentWordState() {
        StringBuilder currentState = new StringBuilder();
        for (char letter : selectedWord.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                currentState.append(letter).append(" ");
            } else {
                currentState.append(HIDDEN_CHAR).append(" ");
            }
        }
        return currentState.toString().trim();
    }

    // Check if the word is completely guessed
    private boolean isWordComplete() {
        for (char letter : selectedWord.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }
}
