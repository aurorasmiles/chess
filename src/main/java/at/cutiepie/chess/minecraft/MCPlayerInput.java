package at.cutiepie.chess.minecraft;

import at.cutiepie.chess.model.Coordinate;
import at.cutiepie.chess.model.Figure;
import at.cutiepie.chess.model.Input;

import java.util.UUID;

public class MCPlayerInput extends Input {
    private final UUID uuid;
    private Figure selectedFigure = null;

    public MCPlayerInput(boolean isWhite, UUID uuid) {
        super(isWhite);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Figure getSelectedFigure() {
        return selectedFigure;
    }

    public void setSelectedFigure(Figure selectedFigure) {
        this.selectedFigure = selectedFigure;
    }

    public void moveFigureTo(Coordinate to) {
        if(this.selectedFigure == null) {
            throw new IllegalStateException("No figure selected");
        }

        moveFigure(this.selectedFigure, to);
    }
}
