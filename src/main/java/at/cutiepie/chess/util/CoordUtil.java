package at.cutiepie.chess.util;

import at.cutiepie.chess.model.Board;
import at.cutiepie.chess.model.Coordinate;
import at.cutiepie.chess.model.Figure;

import java.util.HashSet;
import java.util.Set;

public abstract class CoordUtil {
    public static void addIfPossible(int x, int y, Figure f, Set<Coordinate> coords, Board board) {
        x = f.getCoord().getX() + x;
        y = f.getCoord().getY() + y;
        if (x <= 8 && x >= 1 && y <= 8 && y >= 1) {
            Coordinate c = new Coordinate(x, y);
            if (board.getFigureAt(c).map(color -> color.isWhite() != f.isWhite()).orElse(true)) {
                coords.add(c);
            }
        }
    }
}
