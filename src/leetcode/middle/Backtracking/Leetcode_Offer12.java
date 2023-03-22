package leetcode.middle.Backtracking;/*
 *
 * @Param
 */

public class Leetcode_Offer12 {
    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        String word = "ABCESEEEFS";
        if(new Leetcode_Offer12().exist(board,word)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
    public boolean exist(char[][] board, String word) {
        int row = board.length,column = board[0].length;

        for(int i = 0;i < row;i++){
            for(int j = 0;j < column;j++){
                if(board[i][j] == word.charAt(0)){
                    int[][] loop = new int[row][column];
                    loop[i][j] = 1;
                    if(judge(board,loop,i,j,0,row,column,word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean judge(char[][] board,int[][] loop,int i,int j,int index,int row,int column,String word){
        if(index == word.length() - 1){
            return true;
        }
        if(i < row - 1 && loop[i + 1][j] == 0 && board[i + 1][j] == word.charAt(index + 1)){
            loop[i + 1][j] = 1;
            if(judge(board,loop,i + 1,j,index + 1,row,column,word)){
                return true;
            }
            loop[i + 1][j] = 0;
        }
        if(i > 0 && loop[i - 1][j] == 0 &&  board[i - 1][j] == word.charAt(index + 1)){
            loop[i - 1][j] = 1;
            if(judge(board,loop,i - 1,j,index + 1,row,column,word)){
                return true;
            }
            loop[i - 1][j] = 0;
        }
        if(j < column - 1 &&loop[i][j + 1] == 0 && board[i][j + 1] == word.charAt(index + 1)){
            loop[i][j + 1] = 1;
            if(judge(board,loop,i,j + 1,index + 1,row,column,word)){
                return true;
            }
            loop[i][j + 1] = 0;
        }
        if(j > 0 && loop[i][j - 1] == 0 &&  board[i][j - 1] == word.charAt(index + 1)){
            loop[i][j - 1] = 1;
            if(judge(board,loop,i,j - 1,index + 1,row,column,word)){
                return true;
            }
            loop[i][j - 1] = 0;
        }
        return false;
    }
}
