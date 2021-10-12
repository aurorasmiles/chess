package at.cutiepie.chess.model;

import java.util.HashMap;
import java.util.Map;

public class Coordinate {
    private static Map<Integer, Coordinate> CACHE = new HashMap<>();

    public static Coordinate of(int x, int y) {
        int code = (x << 9) | y;
        synchronized (CACHE) {
            return CACHE.computeIfAbsent(code, i -> new Coordinate(x, y));
        }
    }

    private int x;
    private int y;

    private Coordinate(int x, int y) {
        if (x > 8 || y > 8 || x < 1 || y < 1) {
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
        return Coordinate.of(this.x + x, this.y + y);
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return (x << 9) | y;
    }

    @Override
    public String toString() {
        return ('A' + x - 1) + "" + y;
    }
}
