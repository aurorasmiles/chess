package at.cutiepie.chess.model;

public class Figure {
    private final FigureType type;
    private int x;
    private int y;
    private boolean isWhite;

    public Figure(FigureType type, int x, int y, boolean isWhite) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }
}
