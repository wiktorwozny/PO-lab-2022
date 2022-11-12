package agh.ics.oop;
import static java.lang.System.out;

public class World {

    public static void main(String[] args) {


        out.println("rectangular map:");
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        out.println("grass field:");
        map = new GrassField(10);
        engine = new SimulationEngine(directions, map, positions);
        engine.run();

        out.println("ended\n");
    }
}
