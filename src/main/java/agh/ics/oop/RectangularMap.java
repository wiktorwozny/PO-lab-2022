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

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition())) {
            return false;
        }
        if (!canMoveTo(animal.getPosition())) {
            return false;
        }
        //animal can be placed
        if (animal.getPreviousPosition() != null) {
            this.map.remove(animal.getPreviousPosition());
        }

        this.map.put(animal.getPosition(), animal);

        return true;
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
