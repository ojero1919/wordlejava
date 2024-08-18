import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static final int SIX = 6;

    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner(System.in);
        String[] guess = new String[6]; 

        WordleBoard word = new WordleBoard(); //initialize game class
        //System.out.println(word.getAnswer());
        for(int i = 0; i < SIX; i++){   
            System.out.println("Enter guess "+ (i+1) +": "); // prompt user
            guess[i] = s.nextLine().toUpperCase();

            while(guess[i].length() != 5){ //stop users from entering words longer than 5 letters
                System.out.println("Enter guess "+ (i+1) +": ");
                guess[i] = s.nextLine().toUpperCase();
            }

            while(allLetters(guess[i])){ //stop users from entering numbers or other things
                System.out.println("Enter guess "+ (i+1) +": ");
                guess[i] = s.nextLine().toUpperCase();
            }

            word.setGuess(guess[i]);  //pass guess into wordleboard
        
            System.out.println(word.checkGuess());
            

            System.out.println(word.printKeyboard());

            if(word.winCond()){
                System.out.println("Congrats, you win!");
                break;
            }
        }

        if(word.winCond() == false){
            System.out.println("Sorry, you lost. The answer was " + word.getAnswer());
        }

        s.close();
    }
    public static boolean allLetters(String guess){
        return guess.matches(".*[^a-zA-Z].*"); //stops users from entering non alphabetical characters
    }
}

