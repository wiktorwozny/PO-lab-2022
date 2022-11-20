package agh.ics.oop;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;

public class GrassField extends AbstractWorldMap implements IWorldMap {

    private final int maxX, maxY;
    protected final MapBoundary mapBoundary = new MapBoundary();

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
            this.mapBoundary.addWorldMapElement(new Grass(grassPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }

    @Override
    public void place(Animal animal) {

        super.place(animal);
        this.map.put(animal.getPosition(), animal);

        this.mapBoundary.addWorldMapElement(animal);
    }

    public boolean positionChanged(Object object, Vector2d oldPosition, Vector2d newPosition) {
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

        return super.positionChanged(object, oldPosition, newPosition);
    }

    @Override
    public Vector2d lowerLeftDraw() {

        if (this.map.isEmpty()) {
            return new Vector2d(0, 0);
        }

        Vector2d result = map.keySet().iterator().next();

        for (Vector2d pos: map.keySet()) {
            result = result.lowerLeft(pos);
        }

        return result;

//        return this.mapBoundary.getLowerLeftCorner();
    }

    @Override
    public Vector2d upperRightDraw() {

        if (this.map.isEmpty()) {
            return new Vector2d(0, 0);
        }

        Vector2d result = map.keySet().iterator().next();

        for (Vector2d pos: map.keySet()) {
            result = result.upperRight(pos);
        }

        return result;

//        return this.mapBoundary.getUpperRightCorner();
    }
}
