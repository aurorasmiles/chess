package at.cutiepie.chess.model;

import java.util.List;

public class Board {
    private List<Figure> figures;

    public void resetBoard() {
        figures.clear();

        //ROOKS
        figures.add(new Figure(FigureType.ROOK, 1, 1, true));    //white a1
        figures.add(new Figure(FigureType.ROOK, 8, 1, true));    //white h1
        figures.add(new Figure(FigureType.ROOK, 1, 8, false));   //black a8
        figures.add(new Figure(FigureType.ROOK, 8, 8, false));   //black h8

        //KNIGHTS
        figures.add(new Figure(FigureType.KNIGHT, 2, 1, true));  //white b1
        figures.add(new Figure(FigureType.KNIGHT, 7, 1, true));  //white g1
        figures.add(new Figure(FigureType.KNIGHT, 2, 8, false)); //black b8
        figures.add(new Figure(FigureType.KNIGHT, 7, 8, false)); //black g8

        //BISHOPS
        figures.add(new Figure(FigureType.BISHOP, 3, 1, true));  //white c1
        figures.add(new Figure(FigureType.BISHOP, 6, 1, true));  //white f1
        figures.add(new Figure(FigureType.BISHOP, 3, 8, false)); //black c8
        figures.add(new Figure(FigureType.BISHOP, 6, 8, false)); //black f8

        //QUEENS
        figures.add(new Figure(FigureType.QUEEN, 4, 1, true));   //white d1
        figures.add(new Figure(FigureType.QUEEN, 4, 8, false));  //black d8

        //KINGS
        figures.add(new Figure(FigureType.KING, 5, 1, true));    //white e1
        figures.add(new Figure(FigureType.KING, 5, 8, false));   //black e8

        //PAWNS
        for(int i = 1; i <= 8; i++) {
            figures.add(new Figure(FigureType.PAWN, i, 2, true));  //white a2 to h2
            figures.add(new Figure(FigureType.PAWN, i, 7, false)); //black a7 to h7
        }
    }
}
