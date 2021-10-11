package at.cutiepie.chess.model;

import at.cutiepie.chess.util.CoordUtil;
import org.checkerframework.checker.units.qual.C;

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

    public Set<Coordinate> possibleMoves(Board board) {
        int x = coord.getX();
        int y = coord.getY();
        Set<Coordinate> coords = new HashSet<>();
        switch (type) {
            case ROOK:
                for (int i = x+1; i <= 8; i++) {
                    Coordinate c = new Coordinate(i, y);
                    if (board.getFigureAt(c).isEmpty()) {
                        coords.add(c);
                    } else if (board.getFigureAt(c).map(color -> isWhite() != color.isWhite()).orElse(true)) {
                        coords.add(c);
                        break;
                    } else {
                        break;
                    }
                }
                for (int i = x-1; i >= 1; i--) {
                    Coordinate c = new Coordinate(i, y);
                    if (board.getFigureAt(c).isEmpty()) {
                        coords.add(c);
                    } else if (board.getFigureAt(c).map(color -> isWhite() != color.isWhite()).orElse(true)) {
                        coords.add(c);
                        break;
                    } else {
                        break;
                    }
                }
                for (int i = y+1; i <= 8; i++) {
                    Coordinate c = new Coordinate(x, i);
                    if (board.getFigureAt(c).isEmpty()) {
                        coords.add(c);
                    } else if (board.getFigureAt(c).map(color -> isWhite() != color.isWhite()).orElse(true)) {
                        coords.add(c);
                        break;
                    } else {
                        break;
                    }
                }
                for (int i = y-1; i >= 1; i--) {
                    Coordinate c = new Coordinate(x, i);
                    if (board.getFigureAt(c).isEmpty()) {
                        coords.add(c);
                    } else if (board.getFigureAt(c).map(color -> isWhite() != color.isWhite()).orElse(true)) {
                        coords.add(c);
                        break;
                    } else {
                        break;
                    }
                }
                return coords;
            case KNIGHT:
                CoordUtil.addIfPossible(2, 1, this, coords, board);
                CoordUtil.addIfPossible(1, 2, this, coords, board);
                CoordUtil.addIfPossible(-1, 2, this, coords, board);
                CoordUtil.addIfPossible(-2, 1, this, coords, board);
                CoordUtil.addIfPossible(-2, -1, this, coords, board);
                CoordUtil.addIfPossible(-1, -2, this, coords, board);
                CoordUtil.addIfPossible(1, -2, this, coords, board);
                CoordUtil.addIfPossible(2, -1, this, coords, board);
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
                        CoordUtil.addIfPossible(x, y, this, coords, board);
                    }
                }
                coords.remove(coord);
            case PAWN:
                if (isWhite) {
                    coords.add(coord.add(0, 1));
                    if (coord.getY() == 2) {
                        coords.add(coord.add(0, 2));
                    }
                    CoordUtil.addIfPossible(-1, 1, this, coords, board);
                    CoordUtil.addIfPossible(1, 1, this, coords, board);
                } else {
                    coords.add(coord.add(0, -1));
                    if (coord.getY() == 7) {
                        coords.add(coord.add(0, -2));
                    }
                    CoordUtil.addIfPossible(-1, -1, this, coords, board);
                    CoordUtil.addIfPossible(1, -1, this, coords, board);
                }
                return coords;
        }
        return coords;
    }

    public FigureType getType() {
        return type;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
