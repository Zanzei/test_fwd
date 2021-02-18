/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Enum.Symbol;
import Model.Player;

/**
 *
 * @author Andre
 */
public class BoardLogic {

    private Symbol board[][];
    private Integer totalRow;
    private Integer totalCol;
    private Integer totalCells;
    private Integer turn;

    public BoardLogic(int totalRow, int totalCol) {
        board = new Symbol[totalRow][totalCol];
        this.totalRow = totalRow;
        this.totalCol = totalCol;
        this.totalCells = totalRow * totalCol;
        this.turn = 1;
    }

    public Symbol getBoardValue(int i, int j) {
        if (i < 0 || i >= totalRow) {
            return Symbol.BLANK;
        }
        if (j < 0 || j >= totalCol) {
            return Symbol.BLANK;
        }
        return board[i][j];
    }

    public void setBoardValue(int i, int j, Symbol symbol) {
        if (i < 0 || i >= totalRow) {
            return;
        }
        if (j < 0 || j >= totalCol) {
            return;
        }
        board[i][j] = symbol;
        turn++;
    }

    public boolean isWinner(int row, int col, Player player) {
        //If there hasn't been enough turns to determine a winner,
        //immediately return no winner.
        if (turn < row) {
            return false;
        }
        return isHorizontalCount(row, player)
                || isVerticalCount(col, player)
                || isDiagonalCount(player);
    }

    private boolean isHorizontalCount(int row, Player player) {
        int count = 0;
        for (int j = 0; j < totalCol; j++) {
            if (board[row][j] != null
                    && board[row][j].equals(player.getCharacter())) {
                count++;
            }
            if (isEnoughPoint(count)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVerticalCount(int col, Player player) {
        int count = 0;
        for (int i = 0; i < totalRow; i++) {
            if (board[i][col] != null
                    && board[i][col].equals(player.getCharacter())) {
                count++;
            }
            if (isEnoughPoint(count)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalCount(Player player) {
        int count = 0;
        int colTemp = 0;
        for (int i = 0; i < totalRow; i++) {
            if (colTemp >= totalRow) {
                break;
            }
            if (board[i][colTemp] != null
                    && board[i][colTemp].equals(player.getCharacter())) {
                count++;
            }
            if (isEnoughPoint(count)) {
                return true;
            }
            colTemp++;
        }

        colTemp = 0;
        count = 0;
        for (int i = (totalRow - 1); i >= 0; i--) {
            if (colTemp >= totalRow) {
                break;
            }
            if (board[i][colTemp] != null
                    && board[i][colTemp].equals(player.getCharacter())) {
                count++;
            }
            if (isEnoughPoint(count)) {
                return true;
            }
            colTemp++;
        }
        return false;
    }

    public boolean isDraw() {
        //To check for draws, we just need to check if there is no more cells
        //left.
        return 0 > (totalCells - turn);
    }

    private boolean isEnoughPoint(int point) {
        return point >= totalRow;
    }

    public Symbol[][] getBoard() {
        return board;
    }

    public void setBoard(Symbol[][] board) {
        this.board = board;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public Integer getTotalCol() {
        return totalCol;
    }

    public void setTotalCol(Integer totalCol) {
        this.totalCol = totalCol;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

}
