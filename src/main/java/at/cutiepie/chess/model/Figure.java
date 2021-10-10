package at.cutiepie.chess.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Figure {
    private final FigureType type;
    private Coordinate coord;
    private boolean isWhite;

    public Figure(FigureType type, Coordinate coord, boolean isWhite) {
        this.type = type;
        this.coord = coord;
        this.isWhite = isWhite;
    }

    public Set<Coordinate> possibleMovesByType() {
        Set<Coordinate> coords = new HashSet<>();
        switch (type) {
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
                addIfPossible(2, 1, coords);
                addIfPossible(1, 2, coords);
                addIfPossible(-1, 2, coords);
                addIfPossible(-2, 1, coords);
                addIfPossible(-2, -1, coords);
                addIfPossible(-1, -2, coords);
                addIfPossible(1, -2, coords);
                addIfPossible(2, -1, coords);
                return coords;
            case BISHOP:
                for (int x = coord.getX() + 1, y = coord.getY() + 1; x <= 8 && y <= 8; x++, y++) {
                    coords.add(new Coordinate(x, y));
                }
                for (int x = coord.getX() + 1, y = coord.getY() - 1; x <= 8 && y >= 1; x++, y--) {
                    coords.add(new Coordinate(x, y));
                }
                for (int x = coord.getX() - 1, y = coord.getY() + 1; x >= 1 && y <= 8; x--, y++) {
                    coords.add(new Coordinate(x, y));
                }
                for (int x = coord.getX() - 1, y = coord.getY() - 1; x >= 1 && y >= 1; x--, y--) {
                    coords.add(new Coordinate(x, y));
                }
                return coords;
            case QUEEN:
                for (int x = coord.getX() + 1, y = coord.getY() + 1; x <= 8 && y <= 8; x++, y++) {
                    coords.add(new Coordinate(x, y));
                }
                for (int x = coord.getX() + 1, y = coord.getY() - 1; x <= 8 && y >= 1; x++, y--) {
                    coords.add(new Coordinate(x, y));
                }
                for (int x = coord.getX() - 1, y = coord.getY() + 1; x >= 1 && y <= 8; x--, y++) {
                    coords.add(new Coordinate(x, y));
                }
                for (int x = coord.getX() - 1, y = coord.getY() - 1; x >= 1 && y >= 1; x--, y--) {
                    coords.add(new Coordinate(x, y));
                }
                for (int i = 1; i <= 8; i++) {
                    coords.add(new Coordinate(i, coord.getY()));
                }
                for (int i = 1; i <= 8; i++) {
                    coords.add(new Coordinate(coord.getX(), i));
                }
                coords.remove(coord);
                return coords;
            case KING:
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        addIfPossible(x, y, coords);
                    }
                }
                coords.remove(coord);
            case PAWN:
                if (isWhite) {
                    coords.add(coord.add(0, 1));
                    if (coord.getY() == 2) {
                        coords.add(coord.add(0, 2));
                    }
                    addIfPossible(-1, 1, coords);
                    addIfPossible(1, 1, coords);
                } else {
                    coords.add(coord.add(0, -1));
                    if (coord.getY() == 7) {
                        coords.add(coord.add(0, -2));
                    }
                    addIfPossible(-1, -1, coords);
                    addIfPossible(1, -1, coords);
                }
                return coords;
        }
        return coords;
    }

    private void addIfPossible(int x, int y, Set<Coordinate> coords) {
        x = coord.getX() + x;
        y = coord.getY() + y;
        if (x <= 8 && x >= 1 && y <= 8 && y >= 1) {
            coords.add(new Coordinate(x, y));
        }
    }
}
