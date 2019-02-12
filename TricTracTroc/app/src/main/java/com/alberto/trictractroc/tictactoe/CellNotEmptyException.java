package com.alberto.trictractroc.tictactoe;

class CellNotEmptyException extends Exception {
    private final int id;
    public CellNotEmptyException(int id) {
        super(String.format("Cell %d is not empty",id));
        this.id = id;
    }
}