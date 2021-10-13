package at.cutiepie.chess.minecraft.model;

import at.cutiepie.chess.model.Board;
import at.cutiepie.chess.model.Coordinate;
import at.cutiepie.chess.model.Figure;
import at.cutiepie.chess.model.Input;
import org.bukkit.Location;

import java.util.Optional;

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
    protected void moveFigure(Input i, Figure f, Coordinate to) {
        Coordinate from = f.getCoord();
        Optional<Figure> existing = getFigureAt(to);
        super.moveFigure(i, f, to);
        if (from == to) {//not moved
            return;
        }
        existing.ifPresent(e -> removeFigure(e, to));
        removeFigure(f, from);
        addFigure(f, to);
    }

    public Optional<Coordinate> getCoordinate(Location l) {
        Location offset = l.subtract(origin);
        if (1 <= offset.getBlockX() && offset.getBlockX() <= 8
            && 1 <= offset.getBlockZ() && offset.getBlockZ() <= 8) {
            return Optional.of(Coordinate.of(offset.getBlockX(), offset.getBlockZ()));
        }
        return Optional.empty();
    }

    private void addFigure(Figure f, Coordinate coord) {
        //add figure to field
    }

    private void removeFigure(Figure f, Coordinate coord) {
        //remove figure from field
    }
}
