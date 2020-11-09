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

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length==0)
            return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (dp[i][j]==0){
                    max=Math.max(max,dfsForLongestIncreasing(matrix,i,j,dp,Integer.MIN_VALUE));
                }
            }
        }
        return max;
    }

    private int dfsForLongestIncreasing(int[][] matrix, int i, int j, int[][] dp, int pre) {
        if (i<0||j<0||i>= matrix.length||j>= matrix[0].length)return 0;
        else if (pre>=matrix[i][j]) return 0;
        else if (dp[i][j]!=0)return dp[i][j];
        int path1 = dfsForLongestIncreasing(matrix,i+1,j,dp,matrix[i][j]);
        int path2 = dfsForLongestIncreasing(matrix,i-1,j,dp,matrix[i][j]);
        int path3 = dfsForLongestIncreasing(matrix,i,j+1,dp,matrix[i][j]);
        int path4 = dfsForLongestIncreasing(matrix,i,j-1,dp,matrix[i][j]);
        dp[i][j] =1+ Math.max(Math.max(path1,path2),Math.max(path3,path4));
        return dp[i][j];

    }
}
