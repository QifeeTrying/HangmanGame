import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordManager {
    private List<String> wordList;

    public WordManager(String filePath) {
        wordList = new ArrayList<>();
        loadWordsFromFile(filePath);
    }

    private void loadWordsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Error reading word list file: " + e.getMessage());
        }
    }

    public String getRandomWord() {
        if (wordList.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    public void printWordList() {
        if (wordList.isEmpty()) {
            System.out.println("The word list is empty.");
            return;
        }
        System.out.println("Available Words:");
        for (String word : wordList) {
            System.out.println(word);
        }
    }
}
