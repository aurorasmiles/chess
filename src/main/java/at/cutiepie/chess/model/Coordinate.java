package at.cutiepie.chess.model;

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
}
