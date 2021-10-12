package at.cutiepie.chess.minecraft.model;

import at.cutiepie.chess.model.Board;
import at.cutiepie.chess.model.Coordinate;
import at.cutiepie.chess.model.Figure;
import at.cutiepie.chess.model.Input;
import org.bukkit.Location;

public class MinecraftBoard extends Board {
    private final Location origin;

    public MinecraftBoard(Input inputWhite, Input inputBlack, Location origin) {
        super(inputWhite, inputBlack);
        this.origin = origin;
    }

    public void initBoard() {
        //make checker field
    }

    @Override
    public void clear() {
        getFigures().forEach(f -> removeFigure(f, f.getCoord()));
        super.clear();
    }

    @Override
    public void reset() {
        getFigures().forEach(f -> removeFigure(f, f.getCoord()));
        super.reset();
        getFigures().forEach(f -> addFigure(f, f.getCoord()));
    }

    @Override
    public void moveFigure(Figure f, Coordinate to) {
        Coordinate from = f.getCoord();
        super.moveFigure(f, to);
        if (from == to) {//not moved
            return;
        }
        removeFigure(f, from);
        addFigure(f, to);
    }

    private void addFigure(Figure f, Coordinate coord) {
        //add figure to field
    }

    private void removeFigure(Figure f, Coordinate coord) {
        //remove figure from field
    }
}
