package at.cutiepie.chess.model;

public abstract class Input {
    private Board board;

    public Board getBoard() {
        return board;
    }

    void setBoard(Board board) {
        this.board = board;
    }
}
