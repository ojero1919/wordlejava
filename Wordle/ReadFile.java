//import java.nio.file.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ReadFile {
    //private List<String> wordBank;
    public String randWord() throws IOException{
        List<String> wordBank = new ArrayList<String>();
       
        
        wordBank = Files.readAllLines(Paths.get("sgb-words.txt")); // load words from file

        String randWord;

        Random rand = new Random(); 

        int randIdx = rand.nextInt(wordBank.size()); // find random number 

        randWord = wordBank.get(randIdx).toUpperCase(); //get random word
        
        wordBank.remove(randIdx);

        writeToFile("sgb-words.txt", wordBank); // write remaining words to file

        return randWord;
    }


    public static void writeToFile(String filePath, List<String> wordBank){ //actually write to file 
        try {
            Files.write(Paths.get(filePath), wordBank);
        } catch (IOException e) { 
            e.printStackTrace();
        }
    
    }
}
    
