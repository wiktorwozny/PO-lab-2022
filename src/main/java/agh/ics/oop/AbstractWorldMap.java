package agh.ics.oop;

import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected final HashMap<Vector2d, AbstractWorldMapElement> map = new HashMap<>();

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.map.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(this.lowerLeftDraw(), this.upperRightDraw());
    }

    public void place(Animal animal) {

        if (animal == null) {
            throw new IllegalArgumentException("Null can not be placed on the worldMap");
        }

        if ((this.map.get(animal.getPosition()) instanceof Animal) || animal.getPosition() == null) {
            throw new IllegalArgumentException("Object can not be placed on position " + animal.getPosition() + " There " +
                    "is an animal already!");
        }

        animal.addObserver(this);
    }

    public boolean positionChanged(Object object, Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement worldMapElement = this.map.get(oldPosition);
        this.map.put(newPosition, worldMapElement);
        this.map.remove(oldPosition);

        return true;
    }

    public abstract Vector2d lowerLeftDraw();
    public abstract Vector2d upperRightDraw();


}
