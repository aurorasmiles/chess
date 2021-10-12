package at.cutiepie.chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private final List<Figure> figures = new ArrayList<>(16);

    public void clear() {
        this.figures.clear();
    }

    public void reset() {
        clear();

        //ROOKS
        figures.add(new Figure(FigureType.ROOK, Coordinate.of(1, 1), true));    //white a1
        figures.add(new Figure(FigureType.ROOK, Coordinate.of(8, 1), true));    //white h1
        figures.add(new Figure(FigureType.ROOK, Coordinate.of(1, 8), false));   //black a8
        figures.add(new Figure(FigureType.ROOK, Coordinate.of(8, 8), false));   //black h8

        //KNIGHTS
        figures.add(new Figure(FigureType.KNIGHT, Coordinate.of(2, 1), true));  //white b1
        figures.add(new Figure(FigureType.KNIGHT, Coordinate.of(7, 1), true));  //white g1
        figures.add(new Figure(FigureType.KNIGHT, Coordinate.of(2, 8), false)); //black b8
        figures.add(new Figure(FigureType.KNIGHT, Coordinate.of(7, 8), false)); //black g8

        //BISHOPS
        figures.add(new Figure(FigureType.BISHOP, Coordinate.of(3, 1), true));  //white c1
        figures.add(new Figure(FigureType.BISHOP, Coordinate.of(6, 1), true));  //white f1
        figures.add(new Figure(FigureType.BISHOP, Coordinate.of(3, 8), false)); //black c8
        figures.add(new Figure(FigureType.BISHOP, Coordinate.of(6, 8), false)); //black f8

        //QUEENS
        figures.add(new Figure(FigureType.QUEEN, Coordinate.of(4, 1), true));   //white d1
        figures.add(new Figure(FigureType.QUEEN, Coordinate.of(4, 8), false));  //black d8

        //KINGS
        figures.add(new Figure(FigureType.KING, Coordinate.of(5, 1), true));    //white e1
        figures.add(new Figure(FigureType.KING, Coordinate.of(5, 8), false));   //black e8

        //PAWNS
        for (int i = 1; i <= 8; i++) {
            figures.add(new Figure(FigureType.PAWN, Coordinate.of(i, 2), true));  //white a2 to h2
            figures.add(new Figure(FigureType.PAWN, Coordinate.of(i, 7), false)); //black a7 to h7
        }
    }

    public Set<Coordinate> possibleMoves(Figure f) {
        return f.possibleMoves(this);
    }

    public Optional<Figure> getFigureAt(Coordinate c) {
        return figures.stream().filter(f -> f.getCoord().equals(c)).findFirst();
    }

    public void moveFigure(Figure f, Coordinate to) {
        if (!figures.contains(f)) {
            throw new IllegalArgumentException("Figure not on board");
        }

        //check valid move
        if (!f.possibleMoves(this).contains(to)) {
            throw new IllegalArgumentException("Figure cannot be move to " + to);
        }

        //check for figure on to coord
        Optional<Figure> existing = getFigureAt(to);
        existing.ifPresent(ex -> {
            if (ex.isWhite() == f.isWhite()) {
                throw new IllegalArgumentException("Cannot beat own figure");
            }

            figures.remove(ex);
        });

        f.moveTo(to);
    }

    @Override
    public String toString() {
        return IntStream.range(1, 9)
                       .mapToObj(y -> y + IntStream.range(1, 9)
                               .mapToObj(x -> getFigureAt(Coordinate.of(x, y)).map(f -> f.getType().character()).orElse(" "))
                               .collect(Collectors.joining()))
                       .collect(Collectors.joining("\n"))
               + IntStream.range(1, 9).map(e -> 'A' + e - 1);
    }
}
