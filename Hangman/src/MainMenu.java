import java.util.Scanner;

public class MainMenu {
    private WordManager wordManager;

    public MainMenu(String wordFilePath) {
        wordManager = new WordManager(wordFilePath);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("==== Hangman Game ====");
            System.out.println("1. Start Game");
            System.out.println("2. View Word List");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    GameProcess game = new GameProcess(wordManager);
                    game.startGame();
                    break;
                case 2:
                    wordManager.printWordList();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 3);

        scanner.close();
    }
}
