package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimulationTest {

    @Test
    public void test1() {

        IWorldMap worldMap = new RectangularMap(3, 4);
        Vector2d[] initialPositions = {new Vector2d(1, 0), new Vector2d(2, 2), new Vector2d(0, 0)};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD};

        IEngine simulationEngine = new SimulationEngine(moves, worldMap, initialPositions);
        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);

        simulationEngine.run();

        String result = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(2, 3));
        System.out.println(result);

        String expectedResult =
                " y\\x  0 1 2\n" +
                "  4: -------\n" +
                "  3: | | | |\n" +
                "  2: | | |E|\n" +
                "  1: | |W| |\n" +
                "  0: |N| | |\n" +
                " -1: -------\n";

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test2() {

        IWorldMap worldMap = new RectangularMap(7, 5);
        Vector2d[] initialPositions = {new Vector2d(1, 0), new Vector2d(2, 2)};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.FORWARD};

        IEngine simulationEngine = new SimulationEngine(moves, worldMap, initialPositions);
        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);

        simulationEngine.run();

        String result = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(6, 4));
        System.out.println(result);

        String expectedResult =
                " y\\x  0 1 2 3 4 5 6\n" +
                "  5: ---------------\n" +
                "  4: | | | | | | | |\n" +
                "  3: | | | | | | | |\n" +
                "  2: | |E| | | | | |\n" +
                "  1: |W| | | | | | |\n" +
                "  0: | | | | | | | |\n" +
                " -1: ---------------\n";

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test3() {

        IWorldMap worldMap = new RectangularMap(7, 5);
        Vector2d[] initialPositions = {new Vector2d(1, 0), new Vector2d(2, 2)};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};

        IEngine simulationEngine = new SimulationEngine(moves, worldMap, initialPositions);
        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);

        simulationEngine.run();

        String result = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(6, 4));
        System.out.println(result);

        String expectedResult =
                " y\\x  0 1 2 3 4 5 6\n" +
                "  5: ---------------\n" +
                "  4: | | | | | | | |\n" +
                "  3: | | | | | | | |\n" +
                "  2: | | | | |E| | |\n" +
                "  1: |W| | | | | | |\n" +
                "  0: | | | | | | | |\n" +
                " -1: ---------------\n";

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test4() {

        IWorldMap worldMap = new RectangularMap(10, 5);
        Vector2d[] initialPositions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};

        IEngine simulationEngine = new SimulationEngine(moves, worldMap, initialPositions);
        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);

        simulationEngine.run();

        String result = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(9, 4));
        System.out.println(result);

        String expectedResult =
                " y\\x  0 1 2 3 4 5 6 7 8 9\n" +
                "  5: ---------------------\n" +
                "  4: | | | |N| | | | | | |\n" +
                "  3: | | | | | | | | | | |\n" +
                "  2: | | | | | | | | | | |\n" +
                "  1: | | | | | | | | | | |\n" +
                "  0: | | |S| | | | | | | |\n" +
                " -1: ---------------------\n";

        Assertions.assertEquals(expectedResult, result);
    }


}
