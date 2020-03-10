package com.comp1601.tictactoev2;

/**
 * This class represents the game model. It orchestrates moves, checks for wins/draws, and records score.
 * This class does NOT modify the UI. All methods in this class only modify the state of the game, not how the
 * game is represented on the app.
 */

import android.util.Log;
import android.util.SparseArray;

import androidx.collection.SparseArrayCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class TicTacToeGame{
    private final String TAG = "TicTacToeGame";

    private String[][] gameBoard;
    private SparseArrayCompat<int[]> numToCoordinate; //converts a given square num into an array with its corresponding x and y coordinates
    private HashMap<String, Integer> symbolIntRepresentation; //converts a symbol into an integer representation: "X" is converted to 0 and "O" is converted to 1

    //how many games the player or computer won since the app launched
    private int playerScore;



    private int computerScore;

    //which symbol is the player and computer is associated with ("X" or "Y"), this swaps every game
    private String playerSymbol;
    private String computerSymbol;

    private boolean win;
    private boolean draw;


    private ArrayList<Integer> possibleMoves; //which square numbers are still unmarked

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
    private int[][] columns;
    private int[][] rows;
    private int[][] diagonals;

    //represents which squares made up the line that won the game (this is so we can easily highlight the buttons that won)
    private int[] winningSquares;


    public TicTacToeGame(){
        gameBoard = new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };
        playerScore = 0;
        computerScore = 0;
        playerSymbol = "X";
        computerSymbol = "O";

        rows = new int[][]      {{0, 0},{0,0},{0,0}};
        columns = new int[][]   {{0, 0},{0,0},{0,0}};
        diagonals = new int[][] {{0,0},{0,0}};

        numToCoordinate = new SparseArrayCompat<>();
        numToCoordinate.put(1, new int[]{0,0});
        numToCoordinate.put(2, new int[]{0,1});
        numToCoordinate.put(3, new int[]{0,2});
        numToCoordinate.put(4, new int[]{1,0});
        numToCoordinate.put(5, new int[]{1,1});
        numToCoordinate.put(6, new int[]{1,2});
        numToCoordinate.put(7, new int[]{2,0});
        numToCoordinate.put(8, new int[]{2,1});
        numToCoordinate.put(9, new int[]{2,2});

        symbolIntRepresentation = new HashMap<>();
        symbolIntRepresentation.put("X", 0);
        symbolIntRepresentation.put("O", 1);

        possibleMoves = new ArrayList<>();
        possibleMoves.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));

        win = false;
        draw = false;
    }


    /**
     * Modifies the gameboard accordingly, checks if the move resulted in a draw or win
     * @param symbol "X" or "O"
     * @param squareNum represents where on the board to make the move
     */
    public void makeMove(String symbol, int squareNum){
        //translate squareNum into x and y coordinates
        int[] coordinates = numToCoordinate.get(squareNum);
        int x = coordinates[0];
        int y = coordinates[1];

        //don't do anything if the move isn't possible (the square was already marked)
        if(!possibleMoves.contains(squareNum))
            return;

        //update the list of available squares
        possibleMoves.remove(Integer.valueOf(squareNum));

        //mark the gameboard
        gameBoard[x][y] = symbol;

        //0 is "X", 1 is "Y"
        int symbolInt = symbolIntRepresentation.get(symbol);

        //add to columns, rows, diag count
        rows[x][symbolInt]++;
        columns[y][symbolInt]++;
        if(squareNum == 1 || squareNum == 5 || squareNum == 9)
            diagonals[0][symbolInt]++;
        if(squareNum == 3 || squareNum == 5 || squareNum == 7)
            diagonals[1][symbolInt]++;

        //check win, update which line won
        if(rows[x][symbolInt] == 3){
            win = true;
            this.winningSquares = new int[]{3*x + 1, 3*x+2, 3*x + 3};
        }
        if(columns[y][symbolInt] == 3){
            win = true;
            this.winningSquares = new int[]{y+1, y+4, y+7};
        }
        if(diagonals[0][symbolInt] == 3){
            win = true;
            this.winningSquares = new int[]{1, 5, 9};
        }
        if(diagonals[1][symbolInt] == 3){
            win = true;
            this.winningSquares = new int[]{3, 5, 7};
        }

        //if the game results in a draw (no more moves, no winner)
        if(possibleMoves.size() == 0 && !win){
            draw = true;
            win = true;
        }


        printBoard();
    }


    /**
     * Resets board, and win conditions, swaps player symbols, increments score
     * @param lastSymbol who made the last move
     */
    public void newGame(String lastSymbol){
        //increment score based on who won
        if(!draw){
            if(playerSymbol.equals(lastSymbol))
                playerScore++;
            else
                computerScore++;
        }

        //swap player symbols
        if (playerSymbol.equals("X")){
            playerSymbol = "O";
            computerSymbol = "X";
        }else{
            playerSymbol = "X";
            computerSymbol = "O";
        }

        //reset game board
        gameBoard = new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };

        //reset row, col, and diag counts
        rows = new int[][]      {{0, 0},{0,0},{0,0}};
        columns = new int[][]   {{0, 0},{0,0},{0,0}};
        diagonals = new int[][] {{0,0},{0,0}};

        //reset winning buttons
        winningSquares = null;

        //reset possible moves
        possibleMoves.clear();
        possibleMoves.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));

        //reset win condition
        win = false;
        draw = false;
    }


    /**
     * prints the board in logcat NOTE: logcat not compatible with JUnit Test so we use System.out.println();
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
            //Log.i(TAG, currLine.toString());
            System.out.println(currLine.toString());
        }
        //separate printed boards
        //Log.i(TAG, "--------");
        System.out.println("-----");
    }


    /**
     * Makes a random move if there is no imminent win condition
     * That is: if the player is one move away from winning, the computer will block that move, otherwise it will make a random move;
     * @return the square that the computer makes it move on
     */
    public int makeComputerMove(){
        //generate the best possible move
        int playerSymbolInt = symbolIntRepresentation.get(playerSymbol);

        //INDEX SYSTEM: depending on which line is one away from winning, set these indexes for the for loop that searches for the missing square to fill
        int startingIndex = 0;
        int endingIndex = 0;
        int increment = 1;


        for(int i = 0; i < 3; i ++){
            //find which column is one away from winning
            if (columns[i][playerSymbolInt] == 2){
                startingIndex = i + 1;
                endingIndex = startingIndex + 6;
                increment = 3;

                //look for the square that completes the line and fill it in (blocking the enemy's win condition)
                for(int j = startingIndex; j <= endingIndex; j+= increment){
                    if(possibleMoves.contains(j)){
                        makeMove(computerSymbol, j);
                        return j;
                    }
                }
            }


            //find which row is one away from winning
            if(rows[i][playerSymbolInt] == 2){
                //go through possible squares in that row
                startingIndex = 3*i + 1;
                endingIndex = startingIndex + 2;
                increment = 1;

                //look for the square that completes the line and fill it in (blocking the enemy's win condition)
                for(int j = startingIndex; j <= endingIndex; j+= increment){
                    if(possibleMoves.contains(j)){
                        makeMove(computerSymbol, j);
                        return j;
                    }
                }
            }
        }

        //if diagonal pointing downwards to the right is one away from winning
        if(diagonals[0][playerSymbolInt] == 2){
            startingIndex = 1;
            endingIndex = 9;
            increment = 4;

            //look for the square that completes the line and fill it in (blocking the enemy's win condition)
            for(int j = startingIndex; j <= endingIndex; j+= increment){
                if(possibleMoves.contains(j)){
                    makeMove(computerSymbol, j);
                    return j;
                }
            }
        }
        //if diagonal pointing downwards to the left is one away from winning
        if(diagonals[1][playerSymbolInt] == 2){
            startingIndex = 3;
            endingIndex = 7;
            increment = 2;

            //look for the square that completes the line and fill it in (blocking the enemy's win condition)
            for(int j = startingIndex; j <= endingIndex; j+= increment){
                if(possibleMoves.contains(j)){
                    makeMove(computerSymbol, j);
                    return j;
                }
            }
        }

        //get and return random number from list of possible moves
        Random rand = new Random();
        int compMoveIndex = rand.nextInt(possibleMoves.size());
        int compMove = possibleMoves.get(compMoveIndex);
        makeMove(computerSymbol, compMove);
        return compMove;
    }


    /**
     * For reloading data after screen rotation
     * adds to columns, rows, diagonal counts using the game board
     */
    public void updateWinConArrays(){
        for(int x = 0; x < gameBoard.length; x++){
            for(int y = 0; y < gameBoard.length; y++){
                Log.i("BOARD", "SYMBOL: " + gameBoard[x][y]);

                if(gameBoard[x][y] == "X" || gameBoard[x][y] == "O"){
                    //get the symbol at the coordinates (x, y)
                    String symbol = gameBoard[x][y];
                    int symbolInt = symbolIntRepresentation.get(symbol);

                    //add to each win con array according to the symbol and coordinates
                    rows[x][symbolInt]++;
                    columns[y][symbolInt]++;
                    if(x == y)
                        diagonals[0][symbolInt]++;
                    if((x == 0 && y == 2) ||(x == 1 && y == 1) || (x == 2 && y ==0))
                        diagonals[1][symbolInt]++;
                }

            }
        }
    }


    //getters
    public String[][] getGameBoard(){return this.gameBoard;}
    public String getPlayerSymbol(){return this.playerSymbol;}
    public String getComputerSymbol(){return this.computerSymbol;}
    public int[] getWinningSquares(){return this.winningSquares;}
    public int getPlayerScore(){return this.playerScore;}
    public int getComputerScore(){return this.computerScore;}
    public boolean getWin(){return this.win;}
    public boolean getDraw(){return this.draw;}

    //setters
    public void setGameBoard(String[][] gameBoard) { this.gameBoard = gameBoard; }
    public void setPlayerScore(int playerScore) { this.playerScore = playerScore; }
    public void setComputerScore(int computerScore) { this.computerScore = computerScore; }
    public void setPlayerSymbol(String playerSymbol) { this.playerSymbol = playerSymbol; }
    public void setComputerSymbol(String computerSymbol) { this.computerSymbol = computerSymbol; }
    public void setPossibleMoves(ArrayList<Integer> possibleMoves) { this.possibleMoves = possibleMoves; }
}
