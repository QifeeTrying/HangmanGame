import java.util.HashSet;
import java.util.Scanner;

public class GameProcess {
    private WordManager wordManager;
    private String currentWord;
    private char[] guessedWord;
    private int maxFailures;
    private int failures;
    private HashSet<Character> guessedLetters; // Tracks guessed letters

    private final String[] hangmanArt = {
            """
            _______
            |     |
                  |
                  |
                  |
                  |
         =========
         """,
         """
            _______
            |     |
            O     |
                  |
                  |
                  |
         =========
         """,
         """
            _______
            |     |
            O     |
            |     |
                  |
                  |
         =========
         """,
         """
            _______
            |     |
            O     |
           /|     |
                  |
                  |
         =========
         """,
         """
            _______
            |     |
            O     |
           /|\\    |
                  |
                  |
         =========
         """,
         """
            _______
            |     |
            O     |
           /|\\    |
           /      |
                  |
         =========
         """,
         """
            _______
            |     |
            O     |
           /|\\    |
           / \\    |
                  |
         =========
         GAME OVER!
         """
     };

    public GameProcess(WordManager wordManager) {
        this.wordManager = wordManager;
        this.maxFailures = 6; // Adjust max failures based on art stages
        this.guessedLetters = new HashSet<>();
    }

    public void startGame() {
        currentWord = wordManager.getRandomWord();
        if (currentWord == null) {
            System.out.println("No words available to play. Please check your word list.");
            return;
        }

        guessedWord = new char[currentWord.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
        failures = 0;

        Scanner scanner = new Scanner(System.in);
        while (failures < maxFailures && !isWordGuessed()) {
            System.out.println("\n" + hangmanArt[failures]); // Display hangman art
            System.out.println("Current Word: " + String.valueOf(guessedWord));
            System.out.println("Guessed Letters: " + guessedLetters);
            System.out.println("Failures: " + failures + "/" + maxFailures);
            System.out.print("Enter your guess (a single letter): ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Please enter a single letter.");
                continue;
            }

            char guessedLetter = input.charAt(0);
            if (guessedLetters.contains(guessedLetter)) {
                System.out.println("You've already guessed that letter! Try again.");
                continue;
            }

            guessedLetters.add(guessedLetter);
            if (!processGuess(guessedLetter)) {
                failures++;
                System.out.println("Incorrect guess!");
            } else {
                System.out.println("Good guess!");
            }
        }

        System.out.println("\n" + hangmanArt[failures]); // Final hangman art
        if (isWordGuessed()) {
            System.out.println("Congratulations! You guessed the word: " + currentWord);
        } else {
            System.out.println("Game Over! The correct word was: " + currentWord);
        }
    }

    private boolean processGuess(char letter) {
        boolean correct = false;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == letter && guessedWord[i] == '_') {
                guessedWord[i] = letter;
                correct = true;
            }
        }
        return correct;
    }

    private boolean isWordGuessed() {
        for (char c : guessedWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
}
