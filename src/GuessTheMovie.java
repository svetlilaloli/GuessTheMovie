import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class GuessTheMovie {
    public static  void main(String [] args){

        File file = new File("C:\\Users\\Sony Vaio\\Desktop\\Java\\GuessTheMovie\\src\\moviesTitles.txt");
        List movies = toList(file);
            int randomIndex = (int)(Math.random() * movies.size());
            String movieTitle = movies.get(randomIndex).toString();
            Game game = new Game(movieTitle);
            System.out.println("The movie has " + (game.numberOfLetters() - game.getNumberOfBlankSpaces()) + " letters");
            game.guessMovie();

            if (game.wins()){
                System.out.println("Correct! You win!!!");
            } else  {
                System.out.println("You lose :(");
            }
        }

    public static List toList(File file) {
        List movies = new ArrayList();
        int index = 0;

        try (Scanner scannerFile = new Scanner(file)) {
            // transferring all movies into a list
            while (scannerFile.hasNextLine()) {
                movies.add(index, scannerFile.nextLine());
                index++;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
        }
        return movies;
    }
}
