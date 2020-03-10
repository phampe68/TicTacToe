package com.comp1601.tictactoe;

import android.util.Log;
import android.util.SparseArray;

import java.util.Random;

public class TicTacToeGame {
    private String[][] gameBoard; //game board is represented by a 3x3 2d array of strings (X, or O)
    /*
    COLUMN ROW DIAGONAL GAME DESIGN LOGIC:
        - we create 3 2d arrays
        - the first dimension of each array represents a column, row, or diagonol that could be used to win
        - the second dimension represents how many marks are on each column, and which player marked it:
               [HOW MANY MARKS FOR "X"][HOW MANY MARKS FOR "O"]
        - every time a player makes a move, these 3 arrays are updates accordingly
        - for instance if columns [0][1] = 2, we know that there are 2 marks at the 0'th column with the mark "O"
        - likewise rows[1][0] = 3 means that there are 3 marks in the 1st row with the mark "X" and consequently a winning line
    */
    private int symbol; //symbol is an integer representing "X" or "O", 0 corresponds with "X" and 1 corresponds with "O"
    private int player1Symbol;
    private int player2Symbol;
    private int[][] columns;
    private int[][] rows;
    private int[][] diagonals;
    private int[] scores;
    private SparseArray<int[]> numToCoordinate; //converts a given square num into an array with its corresponding x and y coordinates
    private String[] winningLine;
    private final String[] letterRepresentation = {"X", "O"};


    public TicTacToeGame(){
        //initialize game variables
        gameBoard = new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };
        columns = new int[][]   {{0, 0},{0,0},{0,0}};
        rows = new int[][]      {{0, 0},{0,0},{0,0}};
        diagonals = new int[][] {{0,0},{0,0}};
        scores = new int[] {0, 0};
        symbol = 0; //start the game with symbol 0 representing "X"

        //player 1 starts as "X", player 2 starts as "O"
        player1Symbol = 0;
        player2Symbol = 1;

        numToCoordinate = new SparseArray<>();
        numToCoordinate.put(1, new int[]{0,0});
        numToCoordinate.put(2, new int[]{0,1});
        numToCoordinate.put(3, new int[]{0,2});
        numToCoordinate.put(4, new int[]{1,0});
        numToCoordinate.put(5, new int[]{1,1});
        numToCoordinate.put(6, new int[]{1,2});
        numToCoordinate.put(7, new int[]{2,0});
        numToCoordinate.put(8, new int[]{2,1});
        numToCoordinate.put(9, new int[]{2,2});
    }


    /**
     * marks an umarked square either X or O
     * @param squareNum represents which square the user wishes to mark
     */
    public void makeMove(int squareNum){
        //get coordinates
        int x = numToCoordinate.get(squareNum)[0];
        int y = numToCoordinate.get(squareNum)[1];

        //see if the move was a winning move
        this.winningLine = checkWin(squareNum);

        //modify game board according to symbol and swap the symbol
        if(symbol == 0){
            gameBoard[x][y] = "X";
            symbol = 1;
        }
        else{
            gameBoard[x][y] = "O";
            symbol = 0;
        }
    }


    /**
     * prints the game board in logcat
     */
    public void printBoard(){

        for(int i = 0; i < gameBoard.length; ++i){
            StringBuilder currLine = new StringBuilder();   //represents each row

            for(int j = 0; j < gameBoard.length; ++j){

                if(j == 2){  //don't add a separator for the last element of each row
                    currLine.append(gameBoard[i][j]);
                    continue;
                }
                currLine.append(gameBoard[i][j]).append("|");   //add contents to each row
            }
            //print out the row
            Log.i("BOARD",  currLine.toString());
        }

        //separate printed boards
        Log.i("Board", "-----------------");
    }


    /**
     *  HELPER FUNCTION:
     * @param squareNum represents which square the user wishes to mark
     * @return Returns which column, row, or diagonal line was used to win, returns "GAME IN PROGRESS" if no winner yet
     */
    private String[] checkWin(int squareNum){
        //get coordinates from square number
        int x = numToCoordinate.get(squareNum)[0];
        int y = numToCoordinate.get(squareNum)[1];

        //Check win: (see logic at top)
        columns[x][symbol]++;
        rows[y][symbol]++;
        if(squareNum == 1 || squareNum == 5 || squareNum == 9)
            diagonals[0][symbol]++;

        if(squareNum == 3 || squareNum == 5 || squareNum == 7)
            diagonals[1][symbol]++;

        if(columns[x][symbol] == 3){    //win through horizontal line
            incrementScores();
            return new String[]{"ROW", String.valueOf(x)};
        }
        else if(rows[y][symbol] == 3){  //win through vertical line
            incrementScores();
            return new String[]{"COL", String.valueOf(y)};
        }
        else if(diagonals[0][symbol] == 3){ //win through diagonal pointing downards to the right
            incrementScores();
            return new String[]{"DIAG", "A"};
        }
        else if(diagonals[1][symbol] == 3){ //win through diagonal pointing downwards to the left
            incrementScores();
            return new String[]{"DIAG", "B"};
        }
        return new String[]{"GAME IN PROGRESS"};
    }

    public void resetGame(){
        //swap player symbols
        int temp = player1Symbol;
        player1Symbol = player2Symbol;
        player2Symbol = temp;

        //reset columns, rows, and diagonals
        columns = new int[][]   {{0, 0}, {0, 0}, {0, 0}};
        rows = new int[][]      {{0, 0},{0,0},{0,0}};
        diagonals = new int[][] {{0,0},{0,0}};

        //reset the next mark to 0 (representing X)
        symbol = 0;
    }


    /**
     * helper function: incrament the player's score if their symbol matched with the one that won the game
     */
    public void incrementScores(){
        if(player1Symbol == symbol)
            scores[0] ++;
        else
            scores[1] ++;
    }


    public int[] getScores(){return this.scores;}
    public int getSymbol(){ return this.symbol;}
    public String[] getWinningLine() { return winningLine; }

    //returns the player symbols converted to their letter representations
    public String getPlayer1Symbol(){ return letterRepresentation[player1Symbol]; }
    public String getPlayer2Symbol(){ return letterRepresentation[player2Symbol]; }

}
