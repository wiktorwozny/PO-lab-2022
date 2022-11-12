package agh.ics.oop;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;

public class GrassField extends AbstractWorldMap implements IWorldMap {

    private final int maxX, maxY;

    public GrassField(int amount) {

        this.maxX = (int) Math.sqrt(amount * 10);
        this.maxY = (int) Math.sqrt(amount * 10);

        for (int i = 0; i < amount; i++) {

            int x = ThreadLocalRandom.current().nextInt(0, this.maxX + 1);
            int y = ThreadLocalRandom.current().nextInt(0, this.maxY + 1);

            Vector2d grassPosition = new Vector2d(x, y);
            while (this.objectAt(grassPosition) != null) {
                x = ThreadLocalRandom.current().nextInt(0, this.maxX + 1);
                y = ThreadLocalRandom.current().nextInt(0, this.maxY + 1);
                grassPosition = new Vector2d(x, y);
            }

            this.map.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {

        if (!super.place(animal)) {
            return false;
        }

        this.map.put(animal.getPosition(), animal);

        return true;
    }

    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (this.objectAt(newPosition) instanceof Grass) {
            this.map.remove(newPosition);

            int x = ThreadLocalRandom.current().nextInt(0, this.maxX + 1);
            int y = ThreadLocalRandom.current().nextInt(0, this.maxY + 1);

            Vector2d grassPosition = new Vector2d(x, y);
            while (this.isOccupied(grassPosition)) {
                x = ThreadLocalRandom.current().nextInt(0, this.maxX + 1);
                y = ThreadLocalRandom.current().nextInt(0, this.maxY + 1);
                grassPosition = new Vector2d(x, y);
            }

            this.map.put(grassPosition, new Grass(grassPosition));
        }

        return super.positionChanged(oldPosition, newPosition);
    }

    @Override
    public Vector2d lowerLeftDraw() {

        Vector2d result = map.keySet().iterator().next();

        for (Vector2d pos: map.keySet()) {
            result = result.lowerLeft(pos);
        }

        return result;
    }

    @Override
    public Vector2d upperRightDraw() {

        Vector2d result = map.keySet().iterator().next();

        for (Vector2d pos: map.keySet()) {
            result = result.upperRight(pos);
        }

        return result;
    }
}
