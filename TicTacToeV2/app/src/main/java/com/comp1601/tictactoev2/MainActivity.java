package com.comp1601.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private TicTacToeButton[] buttons;
    private TicTacToeGame game;
    private TextView txtPlayerScore;
    private TextView txtComputerScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        //load saved state of the game if the activity was restarted
        if(savedInstanceState != null){
            init();
            loadGame(savedInstanceState);
        }

    }


    /**
     * Saves all data that is lost when the activity gets destoryed
     * @param savedInstanceState contains all the saved data
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //save the game board (since the gameboard is a 2d array, we have to separate it into 3 1d arrays to store it
        //in savedInstanceState
        String[][] gameBoard = game.getGameBoard();
        String[] row1Data = {gameBoard[0][0], gameBoard[0][1], gameBoard[0][2]};
        String[] row2Data = {gameBoard[1][0], gameBoard[1][1], gameBoard[1][2]};
        String[] row3Data = {gameBoard[2][0], gameBoard[2][1], gameBoard[2][2]};
        savedInstanceState.putStringArray("row1", row1Data);
        savedInstanceState.putStringArray("row2", row2Data);
        savedInstanceState.putStringArray("row3", row3Data);

        //save the scores
        int playerScore = game.getPlayerScore();
        int compScore = game.getComputerScore();
        savedInstanceState.putInt("playerScore", playerScore);
        savedInstanceState.putInt("compScore", compScore);

        //save symbol values
        String playerSymbol = game.getPlayerSymbol();
        String compSymbol = game.getComputerSymbol();
        savedInstanceState.putString("playerSymbol", playerSymbol);
        savedInstanceState.putString("compSymbol", compSymbol);
    }


    /**
     * highlights the line of buttons used to win a game
     */
    public void highlightWinningButtons(){
        //get the winning squares from the game class
        int[] winningButtons = game.getWinningSquares();
        if(winningButtons != null){
            for(int squareNum:winningButtons){
                buttons[squareNum-1].highlightButton();
            }
        }
    }


    /**
     * updates the score textviews on the UI
     */
    public void updateScores(){
        this.txtPlayerScore.setText(String.valueOf(game.getPlayerScore()));
        this.txtComputerScore.setText(String.valueOf(game.getComputerScore()));
    }


    /**
     * Marks a given button with a given symbol (this is mainly used for the computer Move since buttons already get updated onClick)
     * @param squareNum the number associated with the button we want to update
     * @param symbol what symbol to mark the button with
     */
    public void updateButton(int squareNum, String symbol){
        buttons[squareNum -1].markButtonClicked(symbol);

        //check if this was the winning move
        boolean gameWon = game.getWin();
        if(gameWon){ //if the computer won, highlight the buttons and make a new game
            startNewGame(symbol);
        }
    }


    /**
     * handles changes to UI associated with the end of a game
     * @param lastSymbol which symbol ended the game, this is used to determine who won
     */
    public void startNewGame(String lastSymbol){
        highlightWinningButtons();

        //show toast indicating game end result
        if(game.getDraw())
           Toast.makeText(this, "DRAW", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, lastSymbol + " WINS", Toast.LENGTH_SHORT).show();

        //start a new game
        game.newGame(lastSymbol);

        //disable all buttons while timer is going on
        for(TicTacToeButton button:buttons){
            button.setEnabled(false);
        }

        //reset all buttons and update score after a seconds
        new CountDownTimer(5000, 1000){ //set timer for 5 seconds
            @Override
            public void onTick(long millisUntilFinished){}
            public void onFinish(){

                //reset all buttons
                for(TicTacToeButton button: buttons)
                    button.resetButton();

                updateScores();

                String computerSymbol = game.getComputerSymbol();
                //let the computer make the first move if it's playing x
                if(computerSymbol.equals("X")){
                    int compMove = game.makeComputerMove();
                    updateButton(compMove, computerSymbol);
                }
            }
        }.start();
    }


    /**
     * initializes state for Main activity
     */
    private void init(){
        //start new game
        game = new TicTacToeGame();

        //inflate widgets
        buttons = new TicTacToeButton[9];
        buttons[0] = findViewById(R.id.square1);
        buttons[1] = findViewById(R.id.square2);
        buttons[2] = findViewById(R.id.square3);
        buttons[3] = findViewById(R.id.square4);
        buttons[4] = findViewById(R.id.square5);
        buttons[5] = findViewById(R.id.square6);
        buttons[6] = findViewById(R.id.square7);
        buttons[7] = findViewById(R.id.square8);
        buttons[8] = findViewById(R.id.square9);
        txtPlayerScore = findViewById(R.id.txtPlayerScore);
        txtComputerScore = findViewById(R.id.txtCompScore);

        updateScores();
    }


    /**
     * Updates the state of the game and the activity before it was destroyed
     * @param savedInstanceState stores the saved data
     */
    private void loadGame(Bundle savedInstanceState){
        //reload the game board
        String[] row1 = savedInstanceState.getStringArray("row1");
        String[] row2 = savedInstanceState.getStringArray("row2");
        String[] row3 = savedInstanceState.getStringArray("row3");
        String[][] gameBoard = new String[3][3];
        gameBoard[0] = row1;
        gameBoard[1] = row2;
        gameBoard[2] = row3;
        game.setGameBoard(gameBoard);

        //use loaded game board to update row column and diagonal counts
        game.updateWinConArrays();

        //reload the scores
        int playerScore = savedInstanceState.getInt("playerScore");
        int compScore = savedInstanceState.getInt("compScore");
        game.setPlayerScore(playerScore);
        game.setComputerScore(compScore);
        updateScores();

        //reload symbols
        String playerSymbol = savedInstanceState.getString("playerSymbol");
        String compSymbol = savedInstanceState.getString("compSymbol");
        game.setPlayerSymbol(playerSymbol);
        game.setComputerSymbol(compSymbol);

        //reload buttons and possible moves
        ArrayList<String> straightBoard = new ArrayList<>(); //the gameboard arranged as a single list so that it mirrors the arrangement of the buttons array
        ArrayList<Integer>possibleMoves = new ArrayList<>();
        straightBoard.addAll(Arrays.asList(row1));
        straightBoard.addAll(Arrays.asList(row2));
        straightBoard.addAll(Arrays.asList(row3));

        //goes through all squares on the board and marks buttons accordingly (also adds to possible moves)
        for(int i = 0; i < straightBoard.size(); ++i){
            if(straightBoard.get(i) == "O" || straightBoard.get(i) == "X"){
                String markingSymbol = straightBoard.get(i);
                buttons[i].markButtonClicked(markingSymbol);
            }
            else
                possibleMoves.add(i + 1);
        }

        game.setPossibleMoves(possibleMoves);
    }


    public TicTacToeGame getGame(){ return this.game; }
}
