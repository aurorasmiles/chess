package at.cutiepie.chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CoordinateTest {
    @Test
    public void testOf() {
        assertTrue(Coordinate.of(1, 1) == Coordinate.of(1, 1));
    }

    @Test
    public void testAdd() {
        assertTrue(Coordinate.of(1, 1) == Coordinate.of(1, 1).add(0, 0));
        assertTrue(Coordinate.of(2, 2) == Coordinate.of(1, 1).add(1, 1));
    }
}