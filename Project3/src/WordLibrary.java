import java.io.*;
import java.util.Random;

/**
 * WordLibrary
 * <p>
 * stores and checks words to have player guess
 *
 * @author Gaurav Singh, 29939
 * @version October 20 ,2022
 */
public class WordLibrary {

    private String[] library;
    private int seed;
    private Random random;
    private String fileName;

    //reading from text file which contains possible words for user to guess
    public WordLibrary(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String lines = "";
            String line;
            String seedString = br.readLine();
            //reading each line
            do {
                line = br.readLine();
                if (line != null) {
                    lines += line + "\n";
                }
            } while (line != null);
            //creating an array of words
            library = lines.split("\n");
            //getting the seed for the random class
            seed = Integer.parseInt(seedString.substring(6));
            //passing in the desired seed to random class
            random = new Random(seed);
            //data cleanup
            processLibrary();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //checks if word is 5 letters
    public void verifyWord(String word) throws InvalidWordException {
        //checks if a given word is 5 letters if not it throws an exception
        if (word.length() != 5) {
            throw new InvalidWordException("Invalid word!");
        }
    }
    //goes through library and removes unwanted words
    public void processLibrary() {
        String[] array;
        String lines = "";
        for (int i = 0; i < library.length; i++) {
            try {
                verifyWord(library[i]);
            } catch (InvalidWordException e) {
                System.out.println(e.getMessage());
                library[i] = "";
            }
        }
        for (String x : library) {
            if (!x.equals("")) {
                lines += x + "\n";
            }
        }
        array = lines.split("\n");
        library = array;
    }

    //returns random word from library array
    public String chooseWord() {
        return library[random.nextInt(library.length)];
    }

    public String[] getLibrary() {
        return library;
    }

    public void setLibrary(String[] library) {
        this.library = library;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
