package at.cutiepie.chess.model;

public abstract class Input {
    private final boolean isWhite;
    private Board board;
    private boolean turn = false;

    protected Input(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Board getBoard() {
        return board;
    }

    void setBoard(Board board) {
        this.board = board;
    }

    public final boolean isTurn() {
        return turn;
    }

    final void setTurn(boolean turn) {
        this.turn = turn;
    }

    protected void moveFigure(Figure f, Coordinate to) {
        if (!isTurn()) {
            board.moveFigure(this, f, to);
        }
    }
}
