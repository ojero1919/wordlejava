import java.io.IOException;

public class WordleBoard {
    private String guess;
    private String answer;
    private String s = "";
    private boolean winCond = false;
    String[] alpha = new String[26];

    private ReadFile read = new ReadFile(); //open ReadFile classe

    public static final int FIVE = 5;

    public static final String GREEN = "\u001B[42m";
    public static final String YELLOW = "\u001B[43m";
    public static final String GREY = "\u001B[100m";
    public static final String RESET = "\u001B[0m";

    public WordleBoard() throws IOException{
        answer = read.randWord(); // get random word from ReadFile
        for(int i = 0; i < 26; i++){
            alpha[i] = String.valueOf((char)('A' + i)); // fill alpha with alphabet
        }
    }
    public void setGuess(String curGuess){
        guess = curGuess; //recieve user guess
    }

    public String getAnswer(){ 
        return answer;
    }

    public String checkGuess(){
        String result = "";
        if(guess.equals(answer)){
            result = GREEN + answer + RESET;
            winCond = true;
            for(int i = 0; i < FIVE; i++){
                updateKeyboard(guess.charAt(i), 1);
            }
            return result;
        } // if right sent result back

        for (int i = 0; i < FIVE; i++) {
            char guessChar = guess.charAt(i);
            char answerChar = answer.charAt(i);
        
            if (guessChar == answerChar) {
                result += GREEN + guessChar + RESET; // change to green if right place and letter
                updateKeyboard(guessChar, 1);
            } 
            else {
                boolean match = false;
                for (int j = 0; j < FIVE; j++) {
                    if (guessChar == answer.charAt(j)) {
                        match = true; // if letter is present but not right place set to yellow
                        break;
                    }
                }
        
                if (match) {
                    result += YELLOW + guessChar + RESET;
                    updateKeyboard(guessChar, 2);
                } 
                else {
                    result += GREY + guessChar + RESET;
                    updateKeyboard(guessChar, 0);
                }
            }
        }
        return result; 
    }

    

    public String printKeyboard(){
        s = "";
        s += '\n';
        for(int i = 0; i < 26; i++){
            s += alpha[i];
            if(((i+1)%9) == 0){
                s +='\n';
            }
        }
        s += '\n';

        return s;
    }

    public void updateKeyboard(char guessChar, int match){
        for (int i = 0; i < 26; i++) {
            String alphaChar = String.valueOf(alpha[i].charAt(0)); // 
            if (alphaChar.equals(String.valueOf(guessChar)) && match == 1) { // set keyboard character to green
                alpha[i] = GREEN + alpha[i] + RESET;
            } 
            else if(alphaChar.equals(String.valueOf(guessChar)) && match == 2){ // set keyboard character to yellow
                alpha[i] = YELLOW + alpha[i] + RESET;
            }
            else if (alphaChar.equals(String.valueOf(guessChar)) && match == 0) {// set keyboard character to grey
                alpha[i] = GREY + alpha[i] + RESET;
            }
        }
    }

    public boolean winCond(){ // determine if game is won
        if(winCond){
            return winCond;
        }
        return false;
    }

}
