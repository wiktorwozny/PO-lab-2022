package agh.ics.oop;

public class Grass extends AbstractMovableWorldMapElement {

    private Vector2d position;

    public Grass(Vector2d position) {
        super(position);
    }

    @Override
    public String toString() {
        return "*";
    }

}
