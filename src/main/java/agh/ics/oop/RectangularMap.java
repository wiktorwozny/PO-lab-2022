package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d upperRight = new Vector2d(this.width - 1, this.height - 1);

        return (position.precedes(upperRight) && position.follows(new Vector2d(0, 0)) && !isOccupied(position));
    }

    public boolean isInsideTheMap(Vector2d position) {
        Vector2d upperRight = new Vector2d(this.width - 1, this.height - 1);

        return position != null && position.precedes(upperRight) && position.follows(new Vector2d(0, 0));
    }

    @Override
    public void place(Animal animal) {

        super.place(animal);

        if (!isInsideTheMap(animal.getPosition())) {
            animal.removeObserver(this);
            throw new IllegalArgumentException("Object can not be placed on position: " + animal.getPosition() +
                    ". It is outside the map!");
        }

        this.map.put(animal.getPosition(), animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map.get(position);
    }

    public Vector2d lowerLeftDraw() {
        return new Vector2d(0, 0);
    }

    public Vector2d upperRightDraw() {
        return new Vector2d(this.width - 1, this.height - 1);
    }
}
