package hangman_one_player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\jmdav\\OneDrive\\Documents\\CAREERDEVS\\intermedia_curriculm\\HangManGame\\src\\words_alpha.txt"));
        Scanner keyboard = new Scanner(System.in);
        List<String> words = new ArrayList<>();

        while(scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random rand = new Random();

        String word = words.get(rand.nextInt(words.size()));

        System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        Integer wrongCount = 0;
        while(true) {

        printHangedMan(wrongCount);

        if(wrongCount >= 6) {
            System.out.println("You lose!");
            break;
        }

            printWordState(word, playerGuesses);
            if(!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }

            if(printWordState(word, playerGuesses)) {
                System.out.println("You Win!");
                break;
            }

            System.out.println("Please Enter Your Guess For The Word:");
            if(keyboard.nextLine().equals(word)) {
                System.out.println("You Win!");
                break;
            }
            else{
                System.out.println("Nope! Try Again.");
            }
        }
    }

    private static void printHangedMan(Integer wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if(wrongCount >= 1 ) {
            System.out.println(" o");
        }
        if(wrongCount >= 2) {
            System.out.print("\\ ");
            if(wrongCount >= 3) {
                System.out.println("/");
            }
            else{
                System.out.println("");
            }
        }
        if(wrongCount >= 4 ) {
            System.out.println(" |");
        }

        if(wrongCount >= 5) {
            System.out.print("/ ");
            if(wrongCount >= 6) {
                System.out.println("\\");
            }
            else{
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please Enter a Letter?");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for(int i = 0; i < word.length(); i++) {
            if(playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else{
                System.out.print("-");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }
}
