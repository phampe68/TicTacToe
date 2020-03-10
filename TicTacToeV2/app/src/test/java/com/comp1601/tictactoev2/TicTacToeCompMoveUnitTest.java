package com.comp1601.tictactoev2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeCompMoveUnitTest {
    /**
     * This class tests to see if the computer can block winning moves (i.e.
     */


    @Test
    public void horizontalWinConditionX(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 1);
        game.makeMove("X", 2);
        game.makeMove("X", 3);

        assertEquals(true, game.getWin());
    }


    @Test
    public void bestPossibleMoveH1(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 1);
        game.makeMove("X", 2);
        assertEquals(3, game.makeComputerMove());
    }

    @Test
    public void bestPossibleMoveH2(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 4);
        game.makeMove("X", 6);
        assertEquals(5, game.makeComputerMove());
    }

    @Test
    public void bestPossibleMoveH3(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 8);
        game.makeMove("X", 9);
        assertEquals(7, game.makeComputerMove());
    }

    @Test
    public void bestPossibleMoveV1(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 1);
        game.makeMove("X", 4);
        assertEquals(7, game.makeComputerMove());
    }

    @Test
    public void bestPossibleMoveV2(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 2);
        game.makeMove("X", 8);
        assertEquals(5, game.makeComputerMove());
    }

    @Test
    public void bestPossibleMoveV3(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 3);
        game.makeMove("X", 6);
        assertEquals(9, game.makeComputerMove());
    }

    @Test
    public void bestPossibleMoveD1(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 1);
        game.makeMove("X", 5);
        assertEquals(9, game.makeComputerMove());
    }

    @Test
    public void bestPossibleMoveD2(){
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 3);
        game.makeMove("X", 7);
        assertEquals(5, game.makeComputerMove());
    }




}
