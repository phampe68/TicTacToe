package com.comp1601.tictactoev2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;



public class TicTacToeButton extends AppCompatButton implements View.OnClickListener {
    private int buttonNum;
    private MainActivity activity;
    private final String TAG = "TicTacToeButton";

    public TicTacToeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(this);
        this.activity = (MainActivity) context;

        //initialize button number (the button number is the last character of its resource id
        //ex: if    resName: square1    resName: square2
        //then      buttonNum: 1        buttonNum: 2
        String resName = getResources().getResourceEntryName(this.getId());
        //get the last character, convert it to a string, and then store it as an integer
        char lastChar = resName.charAt(resName.length() -1);
        String lastString = String.valueOf(lastChar);
        this.buttonNum = Integer.parseInt(lastString);

    }


    //on click listener for the button
    @Override
    public void onClick(View v) {
        Log.i(TAG, String.valueOf(this.buttonNum));        //log which button was pressed

        //do nothing if the button was already pressed (we know the button was pressed if its text was changed to either X or O)
        if(this.getText() == "X" || this.getText()== "O")
            return;

        //make the move according to which symbol the user is playing
        TicTacToeGame game = activity.getGame();
        String playerSymbol = game.getPlayerSymbol();
        game.makeMove(playerSymbol, this.buttonNum);

        this.markButtonClicked(playerSymbol);

        //start new game if this was the winning move
        boolean gameWon = game.getWin();
        if(gameWon){
            activity.startNewGame(playerSymbol);
            return;
        }

        int compMove = game.makeComputerMove(); //makes the move in the model
        String computerSymbol = game.getComputerSymbol();
        activity.updateButton(compMove, computerSymbol); //updates the move in the view
    }


    /**
     * resets the button's background and text, re-enables button
     */
    public void resetButton(){
        this.setBackgroundResource(R.drawable.tic_tac_toe_button_default);
        this.setText("");
        this.setEnabled(true);
    }


    /**
     * highlights a button to the winning colour
     */
    public void highlightButton(){this.setBackgroundResource(R.drawable.tic_tac_toe_button_win); }


    /**
     * highlights a button to clicked, sets the button's text accordingly
     * @param symbol
     */
    public void markButtonClicked(String symbol){
        this.setText(symbol);
        this.setBackgroundResource(R.drawable.tic_tac_toe_button_clicked);
    }
}
