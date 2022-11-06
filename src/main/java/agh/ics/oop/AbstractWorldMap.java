package agh.ics.oop;

import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap {

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

    public abstract Vector2d lowerLeftDraw();
    public abstract Vector2d upperRightDraw();


}
