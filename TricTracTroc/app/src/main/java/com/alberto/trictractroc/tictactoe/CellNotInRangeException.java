package com.alberto.trictractroc.tictactoe;

public class CellNotInRangeException extends Exception{
    private final int id;
    public CellNotInRangeException(int id) {
        super(String.format("Ceel %d is not in range",id));
        this.id = id;
    }
}
