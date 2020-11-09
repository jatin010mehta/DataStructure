package DS;

public class LeetCode {
    int totalPath = 0;
    public int uniquePathsIII(int[][] grid) {
        int totalZero = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==0)
                    totalZero++;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1)
                    dfsForUniquePath(grid,i,j,totalZero);
            }
        }
        return totalPath;
    }

    private void dfsForUniquePath(int[][] grid, int i, int j, int totalZero) {
        if (i<0||j<0||i>= grid.length||j>=grid[0].length||grid[i][j]==-1||(grid[i][j]==2&&totalZero!=0)||grid[i][j]==99)
            return;
        if (grid[i][j]==2&&totalZero==0)
            totalPath++;
        if (grid[i][j]==0)
            totalZero--;
        int temp = grid[i][j];
        grid[i][j]=99;
        dfsForUniquePath(grid,i+1,j,totalZero);
        dfsForUniquePath(grid,i-1,j,totalZero);
        dfsForUniquePath(grid,i,j+1,totalZero);
        dfsForUniquePath(grid,i,j-1,totalZero);
        grid[i][j]=temp;

    }
}
