import java.util.ArrayList;

/**
 * Word searh puzzle.
 */
public class Puzzle {

    /** Stores the word search grid in 2D array. */
    private char[][] grid;
    /** List of all the words to search for. */
    private ArrayList<String> searchWords = new ArrayList<String>();
    /** Keeps track of the current row. */
    private int currRow = 0;
    /** Holds the size of the grid. */
    private int gridSize;
    /** Limit of grid size. */
    private final int MAXGRID = 100;
    /** Maximum number of words that can be searched for. */
    private final int MAXWORDS = 200;
    /** Holds a list of all the results from the searches. */
    private ArrayList<Results> results = new ArrayList<Results>();

    // Constructors
    Puzzle(final int x) {
        if (x <= MAXGRID) {
            grid = new char[x][x];
            gridSize = grid.length;
        } else {
            System.out.println("Dimensions are too large."
                    + " Max puzzle size is 100x100");
        }
    }

    Puzzle(final char[][] puzzle) {
        if (puzzle.length < MAXGRID) {
            grid = puzzle;
            gridSize = grid.length;
        } else {
            System.out.println("Dimensions are too large."
                    + " Max puzzle size is 100x100");
        }
    }

    Puzzle() {
    }

    /**
     * Sets the size of the Wordsearch grid.
     *
     * @param x length and width size.
     */
    public void setDimensions(final int x) {
        if (grid == null) {
            if (x < MAXGRID) {
                grid = new char[x][x];
                gridSize = grid.length;
            } else {
                System.out.println("Error: Grid size is too large.");
                System.exit(0);
            }
        } else {
            System.out.println("Dimensions have already been set");
        }
    }

    /**
     * Adds a row of characters to the grid.
     *
     * @param row Line of strings from text file.
     */
    public void addRow(final String row) {
        if (currRow < gridSize && row.length() == gridSize) {
            for (int i = 0; i < row.length(); i++) {
                grid[currRow][i] = row.charAt(i);
            }

        } else {
            System.out.println("Error: Unable to add row "
                    + (currRow + 1) + " to the grid.");
            System.exit(0);
        }
        currRow++;
    }

    /**
     * Adds a search word from the text file to the list.
     *
     * @param word Word from the text file to search for.
     */
    public void addSearchWord(final String word) {
        if (searchWords.size() < MAXWORDS) {
            searchWords.add(word);
        } else {
            System.out.println("The list of search words is already full.");
            System.exit(0);
        }
    }

    /**
     * Checks if all the characters have been filled
     * into the grid.
     *
     * @return True if the grid is full.
     */
    public boolean isFull() {
        return currRow == grid.length;
    }

    /** prints the grid to the console. */
    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    /**
     * Getter for searWords list.
     *
     * @return searchWords array list.
     */
    public ArrayList<String> getSearchWords() {
        return searchWords;
    }

    /**
     * Getter for the puzzle grid.
     *
     * @return puzzle grid.
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * Gets the size of the grid.
     *
     * @return grid size.
     */
    public int getSize() {
        return gridSize;
    }

    /**
     * Appends a Results object to the results list.
     *
     * @param r Results object.
     */
    public void addResult(final Results r) {
        results.add(r);
    }

    /**
     * Gets the list of results.
     *
     * @return results.
     */
    public ArrayList<Results> getResults() {
        return results;
    }
}
