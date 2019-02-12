package com.alberto.trictractroc.tictactoe;

public class CellNotEmptyException extends Exception {
    public final int id;
    public CellNotEmptyException(int id) {
        super(String.format("Cell %d is not empty",id));
        this.id = id;
    }
}