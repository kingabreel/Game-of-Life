import java.util.Arrays;
import java.util.Random;

public class Grid{
    private final int row;
    private final int col;

    public Grid(int row, int col) {
        this.row = row;
        this.col = col;
    }

    Cell cell = new Cell();

    public int[][] FirstGrid(String pattern){
        int[][] grid = new int[row][col];
        int rowIndex = 0;
        int colIndex = 0;

        //Special value to get a random cell, when game starts
        if(pattern.equals("rnd")){
            Random rando = new Random();
            for (int i = 0; i < row; i++){
                for (int j = 0; j< col; j++){
                    grid[i][j] = rando.nextInt(0, 2);
                }
            }
        } else {
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);

                if (c == '#') {
                    rowIndex++;
                    colIndex = 0;
                    continue;
                }

                grid[rowIndex][colIndex] = Character.getNumericValue(c);
                colIndex++;
            }
        }
        return grid;
    }

    public void PrintGrid(int[][] grid){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
                System.out.print(" | ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }

    public int[][] updateGrid(int[][] grid) {
        int[][] nextGrid = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                int liveNeighbors = cell.CountNeighbor(grid, i, j, row, col);

                //Applying the Game Rules
                if (grid[i][j] == 0 && liveNeighbors == 3) {
                    nextGrid[i][j] = 1;
                } else if (grid[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    nextGrid[i][j] = 0;
                } else {
                    nextGrid[i][j] = grid[i][j];
                }
            }
        }
        if (Arrays.deepEquals(grid, nextGrid)) {
            return grid;
        } else {
            return nextGrid;
        }
    }
    public int countAliveCells(int[][] grid) {
        int aliveCount = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    aliveCount++;
                }
            }
        }
        return aliveCount;
    }

    public boolean StopInfinite(int [][] grid){
        return grid == updateGrid(grid);
    }
}