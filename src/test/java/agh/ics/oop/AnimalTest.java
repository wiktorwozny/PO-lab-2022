package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {

    @Test
    void testAnimal() {

        Animal tester = new Animal();
        OptionsParser parser = new OptionsParser();
        String[] moves = {"f", "f", "f", "f", "f", "r", "b", "r", "f", "f", "f", "l", "f", "f", "f", "f", "l", "b", "b"};
        MoveDirection[] directions = parser.parse(moves);

        for (MoveDirection direction: directions) {
            tester.move(direction);
        }
        Vector2d v = new Vector2d(4, 0);

        assertTrue(tester.isAt(v));
    }
}
