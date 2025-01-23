import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordManager {
    private List<String> wordList;

    // Constructor to load words from the file
    public WordManager() {
        wordList = new ArrayList<>();
        loadWordsFromFile("gameresources/wordlist.txt"); // File path to the word list
    }

    // Method to load words from a file
    private void loadWordsFromFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Read each line and add it to the word list
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    wordList.add(word);
                }
            }

            scanner.close();

            if (wordList.isEmpty()) {
                System.out.println("Error: Word list file is empty!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Word list file not found at " + filePath);
        }
    }

    // Method to select a random word from the list
    public String getRandomWord() {
        if (wordList.isEmpty()) {
            return null; // Return null if no words are available
        }

        Random random = new Random();
        int index = random.nextInt(wordList.size());
        return wordList.get(index);
    }

    // Method to get all words (for debugging or viewing the list)
    public List<String> getAllWords() {
        return new ArrayList<>(wordList); // Return a copy to protect the original list
    }
}
