package at.cutiepie.chess.minecraft.model;

import at.cutiepie.chess.model.Board;
import at.cutiepie.chess.model.Coordinate;
import at.cutiepie.chess.model.Figure;

public class MinecraftBoard extends Board {
    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void moveFigure(Figure f, Coordinate to) {
        Coordinate from = f.getCoord();
        super.moveFigure(f, to);

    }
}
