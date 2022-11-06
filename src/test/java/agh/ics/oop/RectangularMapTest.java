package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RectangularMapTest {

    @Test
    public void drawLimitsTest() {

        RectangularMap rectangularMap = new RectangularMap(10, 20);

        assertEquals(new Vector2d(0, 0), rectangularMap.lowerLeftDraw());
        assertEquals(new Vector2d(9, 19), rectangularMap.upperRightDraw());

    }

    @Test
    public void isOccupiedTest() {

        RectangularMap rectangularMap = new RectangularMap(12, 8);
        assertFalse(rectangularMap.isOccupied(new Vector2d(5, 6)));

        rectangularMap.place(new Animal(rectangularMap, new Vector2d(3, 3)));
        assertTrue(rectangularMap.isOccupied(new Vector2d(3, 3)));

    }

    @Test
    public void placeTest() {

        RectangularMap rectangularMap = new RectangularMap(10, 20);

        assertTrue(rectangularMap.place(new Animal(rectangularMap, new Vector2d(3, 3))));
        assertFalse(rectangularMap.place(new Animal(rectangularMap, new Vector2d(3, 3))));
        assertFalse(rectangularMap.place(new Animal(rectangularMap, new Vector2d(10, 20))));

    }

    @Test
    public void canMoveToTest() {

        RectangularMap rectangularMap = new RectangularMap(10, 20);

        assertTrue(rectangularMap.canMoveTo(new Vector2d(4, 4)));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(11, 2)));

        rectangularMap.place(new Animal(rectangularMap, new Vector2d(5, 5)));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(5, 5)));

    }

}
