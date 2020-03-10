package com.comp1601.tictactoe;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Random;


/**
 * IMPORTANT:
 * this class uses integer values 0 and 1 for representation of "X" and "O"
 * this is to better handle arrays
 */
public class TicTacToeButton extends AppCompatButton implements View.OnClickListener {
    private int buttonNum; //what number button in the 3x3 square of buttons
    private MainActivity activity;

    /**
     * @param context: the context that the button is associated with (all from Main Activity in this app)
     * @param attrs: defines the button's attributes
     */
    public TicTacToeButton(Context context, AttributeSet attrs ) {
        super(context, attrs);
        this.setOnClickListener(this); //every button from this class will share this class's onClickListener

        //initialize button number (the button number is the last character of its resource id
        //ex: if    resName: square1    resName: square2
        //then      buttonNum: 1        buttonNum: 2
        String resName = getResources().getResourceEntryName(this.getId());
        //get the last character, convert it to a string, and then store it as an integer
        char lastChar = resName.charAt(resName.length() -1);
        String lastString = String.valueOf(lastChar);
        this.buttonNum = Integer.parseInt(lastString);

        //set button background to default
        this.setBackgroundResource(R.drawable.tic_tac_toe_button_default);

        //store the MainActivity context (so we can access its state and behaviour from the onClickListener)
        activity = (MainActivity) context;
    }


    /**
     * Changes the text based on which player's turn it is, makes the corresponding change in the TicTacToeGame model
     * @param v: the view (in this case a button)
     */
    @Override
    public void onClick(View v) {
        //log which button was pressed
        Log.i("BUTTON", String.valueOf(this.buttonNum));

        //do nothing if the button was already pressed (we know the button was pressed if its text was changed to either X or O)
        if(this.getText() == "X" || this.getText()== "O")
            return;

        //get the game from main activity
        TicTacToeGame currGame = activity.getGame();

        //change text
        if(currGame.getSymbol() == 0)
            this.setText("X");
        else
            this.setText("O");

        //make move and show board
        currGame.makeMove(this.buttonNum);
        currGame.printBoard();

        //change background
        this.setBackgroundResource(R.drawable.tic_tac_toe_button_clicked);

        //when someone wins, make a new game
        String winningLine = currGame.getWinningLine()[0];
        if(!winningLine.equals("GAME IN PROGRESS")){
            activity.newGame();
        }
    }


    /**
     * resets the button's background and text, re-enables button
     */
    public void resetButton(){
        this.setBackgroundResource(R.drawable.tic_tac_toe_button_default);
        this.setText("");
        this.setEnabled(true);
    }


    public void highlightButton(){
        this.setBackgroundResource(R.drawable.tic_tac_toe_button_win);

    }


}
