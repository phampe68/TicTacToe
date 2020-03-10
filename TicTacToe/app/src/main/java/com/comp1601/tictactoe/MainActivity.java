package com.comp1601.tictactoe;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{
    private TicTacToeButton[] buttons;
    private TicTacToeGame game;
    private TextView txtPlayer1Score;
    private TextView txtComputerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        txtPlayer1Score = findViewById(R.id.lblScore1);
        txtComputerScore = findViewById(R.id.lblScore2);
        updateScores();
    }


    //called when game ends
    public void newGame(){
        //display winning message
        Toast.makeText(this, "YOU WIN", Toast.LENGTH_SHORT).show();

        //highlight winning line
        highlightButtons();

        //disable all buttons while timer is going on
        for(TicTacToeButton button:buttons){
            button.setEnabled(false);
        }

        //reset all buttons after a 5 seconds
        new CountDownTimer(5000, 1000){ //set timer for 5 seconds
            @Override
            public void onTick(long millisUntilFinished){}
            public void onFinish(){
                //reset all buttons
                for(TicTacToeButton button: buttons)
                    button.resetButton();

                game.resetGame();
                updateScores();
            }
        }.start();
    }


    public void highlightButtons(){
        String[] winningLineInfo = game.getWinningLine();
        String lineType = winningLineInfo[0];
        String lineNum = winningLineInfo[1];

        /*LOGIC:
            - we first detect which type of line won (a row, column, or diagonal)
            - then we refer to the way our buttons array is organized:
                0 1 2
                3 4 5
                6 7 8
            - then we structure our for loop accordingly:
            ROWS:
                - the rows each start at 3 * which row won (ex: row 0 starts at 3*0, row 2 starts at 3*2)
                - we therefore start the loop at 3* the line number
                - the rows incrament by 1 up until the starting value + 3
            COLUMNS:
                - the columns start at the line number (column 0 starts at 0, column 1 starts at 1, column 2 starts at 2)
                - we therefore start the loop at the line number
                - the columns incrament by 3 and end at the line number + 6
            DIAGONALS:
                - both diagonals use button 4
                - diagonal A uses 0 and 8
                - diagonal B uses 2 and 6
         */
        if (lineType.equals("ROW")){
            int lineNumInt = Integer.parseInt(lineNum);
            for(int i = lineNumInt * 3; i < lineNumInt + 3; i++){
                buttons[i].highlightButton();
            }
        }
        if(lineType.equals("COL")){
            int lineNumInt = Integer.parseInt(lineNum);
            for(int i = lineNumInt; i <= lineNumInt + 6; i += 3){
                buttons[i].highlightButton();
            }
        }
        if(lineType.equals("DIAG")){
            buttons[4].highlightButton();
            if(lineNum.equals("A")){
                buttons[0].highlightButton();
                buttons[8].highlightButton();
            }
            else{
                buttons[2].highlightButton();
                buttons[6].highlightButton();
            }
        }
    }


    public void updateScores(){
        int player1Score = game.getScores()[0];
        int computerScore = game.getScores()[1];

        String player1ScoreLabel = "PLAYER 1 (" + game.getPlayer1Symbol() + ") SCORE: " + player1Score;
        String player2ScoreLabel = "COMPUTER (" + game.getPlayer2Symbol() + ") SCORE: " + computerScore;

        txtPlayer1Score.setText(player1ScoreLabel);
        txtComputerScore.setText(player2ScoreLabel);
    }

    public TicTacToeGame getGame(){ return this.game; }


}
