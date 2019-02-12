package com.alberto.trictractroc.tictactoe;

public class Game {
    enum State {
        O,X,EMPTY
    }
    public class Cell {
        private State state;
        public Cell() {
            this.state = State.EMPTY;
        }
        public Cell(State s) {
            this.state = s;
        }
    }

    private Cell[] cells;
    private int moves;
    private boolean gameFinished;
    private static int[][][] winLines = {
            {{1, 2}, {4, 8}, {3, 6}},
            {{0, 2}, {4, 7}},
            {{0, 1}, {4, 6}, {5, 8}},
            {{4, 5}, {0, 6}},
            {{3, 5}, {0, 8}, {2, 6}, {1, 7}},
            {{3, 4}, {2, 8}},
            {{7, 8}, {2, 4}, {0, 3}},
            {{6, 8}, {1, 4}},
            {{6, 7}, {0, 4}, {2, 5}}
    };
    public Game() {
        this.cells = new Cell[9];
        this.init();
    }
    public void reset() {
        this.init();
    }
    private void init() {
        this.moves = 0;
        this.gameFinished = false;
        for(int i=0 ; i<9 ; i++) {
            this.cells[i] = new Cell();
        }
    }
    public void makeMove(int pos,State who) throws CellNotEmptyException,CellNotInRangeException {
        if(pos<0&&pos>8) throw new CellNotInRangeException(pos);
        if(this.cells[pos].state == State.EMPTY) {
            this.cells[pos].state = who;
            this.moves++;
        } else {
            throw new CellNotEmptyException(pos);
        }
    }
    /**
     * @return X or O if winner, EMPTY if no one is winning
     * */
    public State checkWinner(int lastMove) {
        if(this.moves > 4) {
            State player = this.cells[lastMove].state;
            for(int i=0 ; i< winLines[lastMove].length ; i++) {
                int[] line = winLines[lastMove][i];
                if(player == this.cells[line[0]].state &&
                        player == this.cells[line[1]].state) {
                    this.gameFinished = true;
                    return player;
                }
            }
        }
        return State.EMPTY;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }
}
