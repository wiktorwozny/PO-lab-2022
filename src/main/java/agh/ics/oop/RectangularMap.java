package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements IWorldMap {

    private int width, height;
    private HashMap<Vector2d, Animal> map = new HashMap<>();

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
    public boolean isOccupied(Vector2d position) {
        return this.map.containsKey(position);
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

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);

        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(this.width - 1, this.height - 1);
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
