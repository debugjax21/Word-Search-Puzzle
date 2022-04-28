/**
 * Stores info on the word, if it was
 * found, and where it was found in the grid.
 */
public class Results {
    /** Word search word. */
    private String word;
    /**
     * Initial coordinates to the first letter
     * where the word was found.
     */
    private int[] startCoord = new int[2];
    /** Coordinates to the last letter in the grid. */
    private int[] finishCoord = new int[2];
    /** Keeps track if the word was found in the puzzle or not. */
    private boolean found;

    // Constructor for if the word was found.
    Results(final String word, final int initialRow, final int initialCol,
            final int finalRow, final int finalCol) {
        this.word = word;
        startCoord[0] = initialRow;
        startCoord[1] = initialCol;
        finishCoord[0] = finalRow;
        finishCoord[1] = finalCol;
        found = true;
    }

    // Constructor for if the word was not found.
    Results(final String word) {
        this.word = word;
        found = false;
    }

    /**
     * Getter for the word.
     *
     * @return Word Search word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Getter for the initial coordinates
     * where the word was found.
     *
     * @return Two element array.
     */
    public int[] getStart() {
        return startCoord;
    }

    /**
     * Getter for the end coordinates for the location of
     * the last letter in the word.
     *
     * @return Two element array.
     */
    public int[] getFinish() {
        return finishCoord;
    }

    /**
     * Call this method to see if the word was found.
     *
     * @return True if the word was found, false otherwise.
     */
    public boolean wasFound() {
        return found;
    }
}
