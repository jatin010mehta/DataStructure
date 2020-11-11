package DS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> cord = new ArrayList();
        if(matrix.length==0)return cord;

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] paci = new int[row][col];
        int[][] alt =  new int[row][col];


        //first row (pacific)
        for(int i=0;i<col;i++){
            dfs(matrix,0,i,paci,Integer.MIN_VALUE);
        }
        //first col (pacific)
        for(int i=0;i<row;i++){
            dfs(matrix,i,0,paci,Integer.MIN_VALUE);
        }
        //last row (altantic)
        for(int i=0;i<col;i++){
            dfs(matrix,row-1,i,alt,Integer.MIN_VALUE);
        }
        //last col (altantic)
        for(int i=0;i<row;i++){
            dfs(matrix,i,col-1,alt,Integer.MIN_VALUE);
        }

        for(int i=0;i<paci.length;i++){
            for(int j=0;j<paci[0].length;j++){
                if(paci[i][j]==1  && alt[i][j]==1){
                    List<Integer> c = new ArrayList();
                    c.add(i);c.add(j);
                    cord.add(c);
                }
            }
        }

        return cord;
    }

    void dfs(int[][] matrix , int row , int col , int[][] temp,int pre){

        if(row<0 || col<0 || row>matrix.length-1 || col>matrix[0].length-1)return;
        else if(pre>matrix[row][col])return;
        else if(temp[row][col]==1)return;

        temp[row][col] = 1;

        dfs(matrix,row+1,col,temp,matrix[row][col]);
        dfs(matrix,row-1,col,temp,matrix[row][col]);
        dfs(matrix,row,col-1,temp,matrix[row][col]);
        dfs(matrix,row,col+1,temp,matrix[row][col]);
    }
    public boolean hasAllCodes(String s, int k) {
        int count = 0;
        int start = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (count==k){
                set.add(s.substring(start,start+k));
                start++;
                count--;
            }
        }
        return set.size()==Math.pow(2,k);
    }
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet();
        for(int n:nums){
            if(set.contains(n)){
                set.remove(n);
            }
            else
                set.add(n);
        }
        for(int n:set)
            return n;
        return -1;
    }
}
