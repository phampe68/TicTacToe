package com.comp1601.tictactoev2;
import org.junit.Test;

import static org.junit.Assert.*;


public class TicTacToeExampleGameUnitTest {
    @Test
    public void compMoveMiddleGame() {
        //tests for a correct computer move in a partially filled board
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("X", 1);
        game.makeMove("O", 9);
        game.makeMove("X", 4);
        game.makeMove("O", 7);
        game.makeMove("X", 5);
        assertEquals(6, game.makeComputerMove());
    }


    @Test
    public void fullCompGame() {
        //makes 9 computer moves
        TicTacToeGame game = new TicTacToeGame();
        game.setComputerSymbol("X");
        game.makeComputerMove();
        game.setComputerSymbol("O");
        game.makeComputerMove();
        game.setComputerSymbol("X");
        game.makeComputerMove();
        game.setComputerSymbol("O");
        game.makeComputerMove();
        game.setComputerSymbol("X");
        game.makeComputerMove();
        game.setComputerSymbol("O");
        game.makeComputerMove();
        game.setComputerSymbol("X");
        game.makeComputerMove();
        game.setComputerSymbol("O");
        game.makeComputerMove();
        game.setComputerSymbol("X");
        game.makeComputerMove();
    }


    @Test
    public void winTest() {
        //checks to see if a win is detected in a sample game
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("O", 1);
        game.makeMove("X", 5);
        game.makeMove("O", 3);
        game.makeMove("X", 8);
        game.makeMove("O", 2);

        assertEquals(true, game.getWin());
    }


    @Test
    public void sameSquareMoveTest(){
        //checks to see if the game still functions if the same move is played in the same spot
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove("O", 1);
        game.makeMove("X", 5);
        game.makeMove("O", 3);
        game.makeMove("X", 8);
        game.makeMove("O", 2);
        game.makeMove("O", 1);
        game.makeMove("X", 5);
        game.makeMove("O", 3);
        game.makeMove("X", 8);

        String[][] expected = new String[][]{
                {"O", "O", "O"},
                {" ", "X", " "},
                {" ", "X", " "}
        };
        assertArrayEquals(expected, game.getGameBoard());
    }

}
