package at.cutiepie.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Figure {
    private final FigureType type;
    private Coordinate coord;
    private boolean isWhite;

    public Figure(FigureType type, Coordinate coord, boolean isWhite) {
        this.type = type;
        this.coord = coord;
        this.isWhite = isWhite;
    }

    public List<Coordinate> possibleMovesByType() {
        List<Coordinate> coords = new ArrayList<>();
        switch(type) {
            case ROOK:
                for (int i = 1; i <= 8; i++) {
                    coords.add(new Coordinate(i, coord.getY()));
                }
                for (int i = 1; i <= 8; i++) {
                    coords.add(new Coordinate(coord.getX(), i));
                }
                coords.remove(coord);
                return coords;
            case KNIGHT:

        }
        return coords;
    }

    private void addIfPossible(int x, int y, List<Coordinate> coords) {
        x = coord.getX() + x;
        y = coord.getY() + y;
        if (x <= 8 && x >= 1 && y <= 8 && y >= 1) {
            coords.add(new Coordinate(x, y));
        }
    }
}
