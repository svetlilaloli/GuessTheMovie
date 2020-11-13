import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class Game {
    private String movieTitle;
    private int numberOfLetters;
    private int wrongGuesses;
    private char [] guessedChars;
    private char playerGuess;
    private Scanner scanner;
    private int numberOfBlankSpaces;
    private boolean wins;
    private List wrongLetters;

    public Game(String movieTitle){
        this.movieTitle = movieTitle;
        numberOfLetters = this.movieTitle.length();
        scanner = new Scanner(System.in);
        guessedChars = initializeGuessedChars();
        numberOfBlankSpaces = countBlankSpaces();
        wrongGuesses = 0;
        wrongLetters = new ArrayList();
        wins = false;
    }
    private int countBlankSpaces(){
        int spaces = 0;
        for (int i = 0; i < numberOfLetters; i++){
            if (movieTitle.charAt(i) == ' '){
                spaces++;
            }
        }
        return spaces;
    }
    private char [] initializeGuessedChars(){
        char [] guessedChars = new char[numberOfLetters];
        int spaceCount = 0;
        for (int i = 0; i < numberOfLetters; i ++){
            if (movieTitle.charAt(i) == ' '){
                guessedChars[i] = ' ';
                spaceCount++;
            } else{
                guessedChars[i] = '_';
            }
        }
        return guessedChars;
    }
    public void guessMovie() {
        while (wrongGuesses < 10) {
            if (!Objects.equals(movieTitle, new String(guessedChars))) {
                System.out.print("You are guessing: ");
                Stream.of(guessedChars).forEach(System.out::print);
                System.out.println();
                System.out.print("You have guessed " + wrongGuesses + " wrong letters ");
                Stream.of(wrongLetters).forEach(System.out::print);
                System.out.println();
                System.out.println("Guess a letter: ");
                playerGuess = Character.toLowerCase(scanner.next().charAt(0));
                if (movieTitle.contains(Character.toString(playerGuess))) {
                    for (int i = 0; i < numberOfLetters; i++) {
                        if (movieTitle.charAt(i) == playerGuess) {
                            guessedChars[i] = playerGuess;
                        }
                    }
                } else {
                    wrongLetters.add(playerGuess);
                    wrongGuesses++;
                }
            } else {
                wins = true;
                break;
            }
        }
    }
    public int numberOfLetters(){
        return numberOfLetters;
    }
    public int getNumberOfBlankSpaces(){
        return numberOfBlankSpaces;
    }
    public boolean wins(){
        return wins;
    }
}
