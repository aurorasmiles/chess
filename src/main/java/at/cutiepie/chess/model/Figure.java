package at.cutiepie.chess.model;

import at.cutiepie.chess.util.CoordUtil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

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
        Set<Coordinate> coords = new HashSet<>();
        switch (type) {
            case ROOK:
                return possibleMovesRook(board);
            case KNIGHT:
                return possibleMovesKnight(board);
            case BISHOP:
                return possibleMovesBishop(board);
            case QUEEN:
                return possibleMovesQueen(board);
            case KING:
                return possibleMovesKing(board);
            case PAWN:
                return possibleMovesPawn(board);
        }
        return coords;
    }

    private Set<Coordinate> possibleMovesRook(Board board) {
        Set<Coordinate> coords = new HashSet<>();
        Function<Coordinate, Boolean> f = coord -> {
            Optional<Figure> figureAt = board.getFigureAt(coord);
            if (figureAt.isPresent()) {
                if (figureAt.get().isWhite() != isWhite) {
                    coords.add(coord);
                }
                return false;
            } else {
                coords.add(coord);
                return true;
            }
        };
        for (int x = coord.getX() + 1; x <= 8; x++) {
            if (!f.apply(Coordinate.of(x, coord.getY()))) {
                break;
            }
        }
        for (int x = coord.getX() - 1; x >= 1; x--) {
            if (!f.apply(Coordinate.of(x, coord.getY()))) {
                break;
            }
        }
        for (int y = coord.getY() + 1; y <= 8; y++) {
            if (!f.apply(Coordinate.of(coord.getX(), y))) {
                break;
            }
        }
        for (int y = coord.getY() - 1; y >= 1; y--) {
            if (!f.apply(Coordinate.of(coord.getX(), y))) {
                break;
            }
        }
        return coords;
    }

    private Set<Coordinate> possibleMovesKnight(Board board) {
        Set<Coordinate> coords = new HashSet<>();
        CoordUtil.addIfPossible(2, 1, this, coords, board);
        CoordUtil.addIfPossible(1, 2, this, coords, board);
        CoordUtil.addIfPossible(-1, 2, this, coords, board);
        CoordUtil.addIfPossible(-2, 1, this, coords, board);
        CoordUtil.addIfPossible(-2, -1, this, coords, board);
        CoordUtil.addIfPossible(-1, -2, this, coords, board);
        CoordUtil.addIfPossible(1, -2, this, coords, board);
        CoordUtil.addIfPossible(2, -1, this, coords, board);
        return coords;
    }

    private Set<Coordinate> possibleMovesBishop(Board board) {
        Set<Coordinate> coords = new HashSet<>();
        Function<Coordinate, Boolean> f = (Coordinate coord) -> {
            Optional<Figure> figureAt = board.getFigureAt(coord);
            if (figureAt.isPresent()) {
                if (figureAt.get().isWhite() != isWhite) {
                    coords.add(coord);
                }
                return false;
            } else {
                coords.add(coord);
                return true;
            }
        };
        for (int x = coord.getX() + 1, y = coord.getY() + 1; x <= 8 && y <= 8; x++, y++) {
            if (!f.apply(Coordinate.of(x, y))) {
                break;
            }
        }
        for (int x = coord.getX() + 1, y = coord.getY() - 1; x <= 8 && y >= 1; x++, y--) {
            if (!f.apply(Coordinate.of(x, y))) {
                break;
            }
        }
        for (int x = coord.getX() - 1, y = coord.getY() + 1; x >= 1 && y <= 8; x--, y++) {
            if (!f.apply(Coordinate.of(x, y))) {
                break;
            }
        }
        for (int x = coord.getX() - 1, y = coord.getY() - 1; x >= 1 && y >= 1; x--, y--) {
            if (!f.apply(Coordinate.of(x, y))) {
                break;
            }
        }
        return coords;
    }

    private Set<Coordinate> possibleMovesQueen(Board board) {
        Set<Coordinate> coords = new HashSet<>();
        coords.addAll(possibleMovesRook(board));
        coords.addAll(possibleMovesBishop(board));
        return coords;
    }

    private Set<Coordinate> possibleMovesKing(Board board) {
        Set<Coordinate> coords = new HashSet<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                CoordUtil.addIfPossible(x, y, this, coords, board);
            }
        }
        coords.remove(coord);
        return coords;
    }

    private Set<Coordinate> possibleMovesPawn(Board board) {
        Set<Coordinate> coords = new HashSet<>();

        if (isWhite) {
            CoordUtil.addIfPossible(0, 1, this, coords, board);
            if (coord.getY() == 2) {
                CoordUtil.addIfPossible(0, 2, this, coords, board);
            }

            //beat
            if (board.getFigureAt(coord.add(-1, 1)).map(e -> e.isWhite() != isWhite).orElse(false)) {
                CoordUtil.addIfPossible(-1, 1, this, coords, board);
            }
            if (board.getFigureAt(coord.add(1, 1)).map(e -> e.isWhite() != isWhite).orElse(false)) {
                CoordUtil.addIfPossible(1, 1, this, coords, board);
            }

            //beat '
            if (coord.getY() == 5) {
                if (board.getFigureAt(coord.add(-1, 0)).map(e -> e.isWhite() != isWhite).orElse(false)) {
                    CoordUtil.addIfPossible(-1, 0, this, coords, board);
                }
                if (board.getFigureAt(coord.add(1, 0)).map(e -> e.isWhite() != isWhite).orElse(false)) {
                    CoordUtil.addIfPossible(1, 0, this, coords, board);
                }
            }
        } else {
            CoordUtil.addIfPossible(0, -1, this, coords, board);
            if (coord.getY() == 7) {
                CoordUtil.addIfPossible(0, -2, this, coords, board);
            }
            if (board.getFigureAt(coord.add(-1, -1)).map(e -> e.isWhite() != isWhite).orElse(false)) {
                CoordUtil.addIfPossible(-1, -1, this, coords, board);
            }
            if (board.getFigureAt(coord.add(1, -1)).map(e -> e.isWhite() != isWhite).orElse(false)) {
                CoordUtil.addIfPossible(1, -1, this, coords, board);
            }

            //beat '
            if (coord.getY() == 4) {
                if (board.getFigureAt(coord.add(-1, 0)).map(e -> e.isWhite() != isWhite).orElse(false)) {
                    CoordUtil.addIfPossible(-1, 0, this, coords, board);
                }
                if (board.getFigureAt(coord.add(1, 0)).map(e -> e.isWhite() != isWhite).orElse(false)) {
                    CoordUtil.addIfPossible(1, 0, this, coords, board);
                }
            }
        }
        return coords;
    }

    public FigureType getType() {
        return type;
    }

    public Coordinate getCoord() {
        return coord;
    }

    /*package private */ void moveTo(Coordinate to) {
        this.coord = to;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
