
// Project Prolog
// Purpose: Write a program that takes an input
// text file from the command line and finds if
// the words are in the puzzle.
// The input file for the program will have the
// letters in the word search grid, followed by a blank
// line and then words to find in the puzzle.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Search {
    /**
     * In main the program reads in the text
     * file from the command line, creates a word
     * search puzzle, searches all the words given from
     * the file, and prints the results.
     *
     * @param args input text file.
     */
    public static void main(final String[] args) {

        Search search = new Search();
        Scanner fGrid = null;
        try {
            fGrid = new Scanner(new File(args[0]));
        } catch (FileNotFoundException x) {
            System.out.println("File open failed.");
            x.printStackTrace();
            System.exit(0); // TERMINATE THE PROGRAM
        }
        Puzzle puzzle = search.createPuzzle(fGrid);
        fGrid.close();
        search.searchAllWords(puzzle);
        puzzle.printGrid();
        search.printResults(puzzle);
    }

    private Puzzle createPuzzle(final Scanner fGrid) {
        int dimensions;
        String line;
        Puzzle puzzle = new Puzzle();
        if (fGrid.hasNextLine()) {
            line = fGrid.nextLine();
            dimensions = line.length();
            puzzle.setDimensions(dimensions);
            puzzle.addRow(line);
        } else {
            System.out.println("File is empty.");
            System.exit(0);
        }
        // Creates the grid.
        while (fGrid.hasNextLine()) {
            if (!puzzle.isFull()) {
                puzzle.addRow(fGrid.nextLine());
            } else {
                line = fGrid.nextLine();
                // Adds the words to search for
                if (!line.isBlank()) {
                    puzzle.addSearchWord(line);
                }
            }
        }
        return puzzle;
    }

    /**
     * Uses Puzzle object to search through all the words
     * in the puzzle.
     *
     * @param puzzle Puzzle object.
     */
    public void searchAllWords(final Puzzle puzzle) {
        for (String word : puzzle.getSearchWords()) {
            searchWord(puzzle, word);
        }
    }

    /**
     * Iterates through the grid looking to match the first
     * letter of the word. If it finds a match, it searches
     * in all directions to see if the word is at that spot.
     *
     * @param puzzle Puzzle object.
     * @param word   Word being searched for in the puzzle.
     */
    public void searchWord(final Puzzle puzzle, final String word) {
        char[][] grid = puzzle.getGrid();
        int gridSize = puzzle.getSize();
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (grid[row][col] == word.charAt(0)) {
                    // Search all directions
                    if (wordInGrid(puzzle, row, col, word)) {
                        return;
                    }
                }
            }
        }
        // Word was not in the grid.
        puzzle.addResult(new Results(word));
    }

    /**
     * Search in all 8 directions in the grid for the word.
     * If the word is found it returns true and creates
     * a Results object to store the word and where it was found.
     *
     * @param puzzle Puzzle object.
     * @param row    Row of the grid.
     * @param col    Column of the grid.
     * @param word   Word to search for.
     * @return True if the word was found, false otherwise.
     */
    private boolean wordInGrid(final Puzzle puzzle, final int row,
            final int col,
            final String word) {
        // For searching in all 8 directions.
        char[][] grid = puzzle.getGrid();
        final int[] xCoord = { 1, 1, 0, -1, -1, -1, 0, 1 };
        final int[] yCoord = { 0, 1, 1, 1, 0, -1, -1, -1 };
        int size = grid.length;
        int nthChar;
        for (int direct = 0; direct < xCoord.length; direct++) {
            int nextCol = col + xCoord[direct];
            int nextRow = row + yCoord[direct];

            for (nthChar = 1; nthChar < word.length(); ++nthChar) {
                // Break if it's out of the grids bounds.
                if (nextCol >= size || nextCol < 0 || nextRow >= size
                        || nextRow < 0) {
                    break;
                }
                if (grid[nextRow][nextCol] != word.charAt(nthChar)) {
                    break;
                }
                // Keep searching in same direction
                if (nthChar < word.length() - 1) {
                    nextCol += xCoord[direct];
                    nextRow += yCoord[direct];
                }
            }
            if (nthChar == word.length()) {
                // Found the word!
                puzzle.addResult(new Results(word, row, col, nextRow, nextCol));
                return true;
            }
        }
        return false;
    }

    /**
     * Prints each word that was searched for in the
     * puzzle. States if it was found and the start and
     * end position the word was found at.
     *
     * @param puzzle Puzzle object
     */
    public void printResults(final Puzzle puzzle) {
        for (Results result : puzzle.getResults()) {
            if (result.wasFound()) {
                System.out.println(result.getWord() + " found at"
                        + " start: " + result.getStart()[0] + ","
                        + result.getStart()[1] + " end: "
                        + result.getFinish()[0] + ","
                        + result.getFinish()[1] + "\n");
            } else {
                System.out.println(result.getWord() + " not found\n");
            }
        }
    }

}
