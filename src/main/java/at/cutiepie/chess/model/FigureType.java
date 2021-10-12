package at.cutiepie.chess.model;

public enum FigureType {
    PAWN("P"),
    ROOK("R"),
    KNIGHT("K"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private final String character;

    FigureType(String character) {
        this.character = character;
    }

    public String character() {
        return character;
    }
}
