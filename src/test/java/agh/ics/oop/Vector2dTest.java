package agh.ics.oop;

import org.junit.jupiter.api.Test;

import javax.lang.model.util.AbstractElementVisitor14;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    void testEquals() {

        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(1, 1);

        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));
    }

    @Test
    void testToString() {

        Vector2d result = new Vector2d(1, 10);
        assertEquals("(1,10)", result.toString(), "wrong toString message");
    }

    @Test
    void testPrecedes() {

        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 1);

        assertTrue(v1.precedes(v2), "wrong .precedes method result");
        assertTrue(v1.precedes(v1), "wrong .precedes method result");
        assertFalse(v2.precedes(v1), "wrong .precedes method result");
    }

    @Test
    void testFollows() {

        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 1);

        assertTrue(v2.follows(v1), "wrong .follow method result");
        assertTrue(v1.follows(v1), "wrong .follow method result");
        assertFalse(v1.follows(v2), "wrong .follow method result");
    }

    @Test
    void testUpperRight() {

        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 1);
        Vector2d r1 = new Vector2d(2, 2);
        Vector2d r2 = new Vector2d(1, 2);

        assertEquals(r1, v1.upperRight(v2));
        assertEquals(r2, v1.upperRight(v1));
    }

    @Test
    void testLowerLeft() {

        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 1);
        Vector2d r1 = new Vector2d(1, 1);
        Vector2d r2 = new Vector2d(1, 2);

        assertEquals(r1, v1.lowerLeft(v2));
        assertEquals(r2, v1.lowerLeft(v1));
    }

    @Test
    void testAdd() {

        Vector2d v1 = new Vector2d(4, 5);
        Vector2d v2 = new Vector2d(-1, -5);
        Vector2d r1 = new Vector2d(8, 10);
        Vector2d r2 = new Vector2d(3, 0);

        assertEquals(r1, v1.add(v1));
        assertEquals(r2, v1.add(v2));
    }

    @Test
    void testSubstract() {

        Vector2d v1 = new Vector2d(4, 5);
        Vector2d v2 = new Vector2d(-1, -5);
        Vector2d r1 = new Vector2d(0, 0);
        Vector2d r2 = new Vector2d(5, 10);

        assertEquals(r1, v1.substract(v1));
        assertEquals(r2, v1.substract(v2));
    }

    @Test
    void testOpposite() {

        Vector2d v1 = new Vector2d(4, 5);
        Vector2d v2 = new Vector2d(0, 0);
        Vector2d r1 = new Vector2d(-4, -5);

        assertEquals(r1, v1.opposite());
        assertEquals(v2, v2.opposite());
    }
}
