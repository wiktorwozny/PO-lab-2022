package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractMovableWorldMapElement extends AbstractWorldMapElement {

    private final ArrayList<IPositionChangeObserver> positionObservers = new ArrayList<>();

    public AbstractMovableWorldMapElement(Vector2d position) {
        super(position);
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.positionObservers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        this.positionObservers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer: this.positionObservers) {
            observer.positionChanged(this, oldPosition, newPosition);
        }
    }


}
