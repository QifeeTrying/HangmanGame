import java.util.Scanner;

public class MainMenu {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("===== HANGMAN GAME =====");
            System.out.println("1. Start Game");
            System.out.println("2. View Rules");
            System.out.println("3. View Word List");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Read user input
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number (1-4).");
                continue;
            }

            // Handle menu choices
            switch (choice) {
                case 1:
                    GameProcess gameProcess = new GameProcess(); // Initialize GameProcess
                    gameProcess.startGame(); // Start the game
                    break;
                case 2:
                    displayRules();
                    break;
                case 3:
                    displayWordList();
                    break;
                case 4:
                    System.out.println("Exiting the game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1, 2, 3, or 4.");
            }
        }

        scanner.close();
    }

    private void displayRules() {
        System.out.println("\n===== HANGMAN RULES =====");
        System.out.println("1. The computer selects a random word.");
        System.out.println("2. You have to guess the word one letter at a time.");
        System.out.println("3. Each incorrect guess adds a part to the hangman.");
        System.out.println("4. If the hangman is complete, you lose.");
        System.out.println("5. Guess the word before the hangman is complete to win!");
        System.out.println();
    }

    private void displayWordList() {
        System.out.println("\n===== WORD LIST =====");
        WordManager wordManager = new WordManager();
        for (String word : wordManager.getAllWords()) {
            System.out.println(word);
        }
        System.out.println(); // Add a blank line for readability
    }
}
