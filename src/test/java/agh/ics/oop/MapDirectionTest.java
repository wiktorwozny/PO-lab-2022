package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    @Test
    void testNext() {
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next(), "wrong result in north.next");
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next(), "wrong result in east.next");
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next(), "wrong result in south.next");
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next(), "wrong result in west.next");
    }

    @Test
    void testPrevious() {
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous(), "wrong result in north.previous");
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous(), "wrong result in east.previous");
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous(), "wrong result in south.previous");
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous(), "wrong result in west.previous");
    }
}
