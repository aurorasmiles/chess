package at.cutiepie.chess.model;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        if (x > 8 || y > 8 || x < 1 || y <1) {
            throw new IllegalArgumentException("Invalid chess coordinates: " + x + "/" + y);
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate add(int x, int y) {
       return new Coordinate(this.x + x, this.y + y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return (x << 9) | y;
    }
}
