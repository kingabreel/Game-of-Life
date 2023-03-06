public class Cell {
    public int CountNeighbor(int[][] grid, int x, int y, int row, int column){
        int count = 0;

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){

                if ((x == 0 && i == -1) || (y == 0 && j == -1) || (i == 0 && j == 0)){
                    continue;
                }else {

                    int neighborRow = (x + i + grid.length) % row;
                    int neighborCol = (y + j + grid.length) % column;

                    count += grid[neighborRow][neighborCol];
                }
            }
        }
        return count;
    }
}
