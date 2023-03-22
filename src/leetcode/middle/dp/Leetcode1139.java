package leetcode.middle.dp;/*
 *
 * @Param
 */

public class Leetcode1139 {


    public int largest1BorderedSquare(int[][] grid) {
        int row = grid.length,column = grid[0].length;
        int[][] left = new int[row + 1][column + 1]; //记录从该点向右最多有多少个1
        int[][] up = new int[row + 1][column + 1]; //记录从该点向上最多有多少个1

        int maxBorder = 0;
        for(int i = 1;i <= row;i++){
            for(int j = 1;j <= column;j++){
                if(grid[i - 1][j - 1] == 1){
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;

                    int border = Math.min(left[i][j], up[i][j]);
                    while(left[i - border + 1][j] < border || up[i][j - border + 1] < border){
                        border--;
                    }
                    maxBorder = Math.max(maxBorder,border);
                }
            }
        }
        return maxBorder * maxBorder;
    }
}
