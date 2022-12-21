/**
 * WordGuesser
 * <p>
 * checks if player is guessing the correct word
 *
 * @author Gaurav Singh, 29939
 * @version October 20 ,2022
 */
public class WordGuesser {
    private String[][] playingField;
    private int round;
    private String solution;

    public WordGuesser(String solution) {
        this.solution = solution;
        round = 1;
        playingField = new String[5][5];
        //creating empty 2d array will all cells filled by a space
        for (int i = 0; i < playingField.length; i++) {
            for (int j = 0; j < playingField[i].length; j++) {
                playingField[i][j] = " ";
            }

        }
    }


    public String[][] getPlayingField() {
        return playingField;
    }

    public int getRound() {
        return round;
    }

    public String getSolution() {
        return solution;
    }


    public void setPlayingField(String[][] playingField) {
        this.playingField = playingField;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    // compares a guess with a solution and updates the playing field
    public boolean checkGuess(String guess) throws InvalidGuessException {
        // if the guess is not 5 letters then throw exception
        if (!(guess.length() == 5)) {
            throw new InvalidGuessException("Invalid Guess!");
        }

        int index = 0;
        for (char x : guess.toCharArray()) { // parses through each character in a guessed word
            if (x == solution.charAt(index)) { // checks if the character is in the correct place
                playingField[round - 1][index] = "'" + x + "'";
            } else if (solution.contains(String.valueOf(x))) { // checks if the character is anywhere in the solution
                playingField[round - 1][index] = "*" + x + "*";
            } else { // incorrect letter
                playingField[round - 1][index] = "{" + x + "}";
            }
            index++;
        }
        return solution.equals(guess); //returns true if the guess matches the solution

    }

    // displays the playing field
    public void printField() {
        for (int i = 0; i < playingField.length; i++) {
            for (int j = 0; j < playingField[i].length - 1; j++) {
                System.out.print(playingField[i][j]);
                System.out.print(" | ");
            }
            System.out.println(playingField[i][4]);

        }

    }


}