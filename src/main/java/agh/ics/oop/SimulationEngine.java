package agh.ics.oop;

import static java.lang.System.out;
import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private final IWorldMap map;
    private final MoveDirection[] moves;
    private final ArrayList<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions) {

        this.moves = moves;
        this.map = map;

        for (Vector2d position: initialPositions) {
            animals.add(new Animal(this.map, position));
        }

        for (Animal animal: this.animals) {
            if (this.map.canMoveTo(animal.getPosition())) {
                this.map.place(animal);
            }
        }
    }

    @Override
    public void run() {

        for (int i = 0; i < this.moves.length; i++) {
            this.animals.get(i % this.animals.size()).move(this.moves[i]);
            out.println(map);
        }
    }
}
