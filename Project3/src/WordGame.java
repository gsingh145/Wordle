import java.io.*;
import java.util.Scanner;

/**
 * WordGame
 * <p>
 * menu to display the game to the console
 *
 * @author Gaurav Singh, 29939
 * @version October 20 ,2022
 */
public class WordGame {
    //all prompts
    public static String welcome = "Ready to play?";
    public static String yesNo = "1.Yes\n2.No";
    public static String noPlay = "Maybe next time!";
    public static String currentRoundLabel = "Current Round: ";
    public static String enterGuess = "Please enter a guess!";
    public static String winner = "You got the answer!";
    public static String outOfGuesses = "You ran out of guesses!";
    public static String solutionLabel = "Solution: ";
    public static String incorrect = "That's not it!";
    public static String keepPlaying = "Would you like to make another guess?";
    public static String fileNameInput = "Please enter a filename";


    //writes to the game log file in order to keep persistent data for each game between different runs of the program
    public static void updateGameLog(String solution, String[] guesses, boolean solved) {

        int game = 0;
        try {
            //creates a file with gamelog.txt as its name if not already created
            new File("gamelog.txt").createNewFile();

            BufferedReader br = new BufferedReader(new FileReader("gamelog.txt"));
            String line = br.readLine();
            if (line != null) {
                game = Integer.parseInt(line.substring(17)); //extracting only the # of games played
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("gamelog.txt"));

            //records old game data
            String oldText = "";
            String oldGameInfo = "";
            do {
                oldGameInfo = br.readLine();
                if (oldGameInfo != null) {
                    oldText += oldGameInfo + "\n";
                }
            } while (oldGameInfo != null);


            bw.write("Games Completed: " + (game + 1) + "\n"); //updates number of games completed
            bw.write(oldText); //Records data from old runs
            //Records data from the current run
            bw.write("Game " + (game + 1) + "\n");
            bw.write("- Solution: " + solution + "\n");

            //creating a string of guess words seperated by commas
            String guessList = "";
            for (int i = 0; i < guesses.length - 1; i++) {
                guessList += guesses[i] + ",";
            }
            guessList += guesses[guesses.length - 1];


            bw.write("- Guesses: " + guessList + "\n"); //displaying the list of guesses
            String option = solved ? "Yes" : "No"; // if solved is true then option = yes otherwise option = no
            bw.write("- Solved: " + option + "\n"); //writes whether a word was solved by the user
            br.close();
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(fileNameInput); //asking for file name
        String fileName = scanner.nextLine();
        WordLibrary library = new WordLibrary(fileName);


        int ready = 0;
        do {
            System.out.println(welcome);
            System.out.println(yesNo);
            ready = scanner.nextInt();
            scanner.nextLine();
            if (ready == 1) {
                WordGuesser guesser = new WordGuesser(library.chooseWord());
                boolean correct = false;
                boolean cont = true;
                String guesses = "";
                do {
                    System.out.println(currentRoundLabel + guesser.getRound());
                    guesser.printField();
                    System.out.println(enterGuess);
                    String guess = scanner.nextLine();
                    guesses += guess + " "; //creating string of all guesses made by the user
                    try {
                        correct = guesser.checkGuess(guess);
                    } catch (InvalidGuessException e) {
                        System.out.println(e.getMessage());
                    }
                    if (correct) {
                        System.out.println(winner);
                        guesser.printField();
                    } else if (guesser.getRound() < 5) {
                        System.out.println(incorrect);
                        System.out.println(keepPlaying);
                        System.out.println(yesNo);
                        cont = scanner.nextInt() == 1;
                        scanner.nextLine();
                    }
                    guesser.setRound(guesser.getRound() + 1);
                } while (guesser.getRound() <= 5 && !correct && cont);
                if (!correct && cont) {
                    System.out.println(outOfGuesses);
                    System.out.println(solutionLabel + guesser.getSolution());
                    guesser.printField();
                }
                if (!cont) {
                    guesser.printField();
                }
                updateGameLog(guesser.getSolution(), guesses.split(" "), correct);
            } else {
                System.out.println(noPlay);
            }
        } while (ready == 1);


    }


}
