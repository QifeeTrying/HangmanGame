public class HangmanGame {
    public static void main(String[] args) {
        String wordFilePath = "gameresources/wordlist.txt"; // Path to your word list file
        MainMenu mainMenu = new MainMenu(wordFilePath);
        mainMenu.displayMenu();
    }
}
