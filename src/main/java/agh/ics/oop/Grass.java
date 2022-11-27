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

    public String toGuiString() {
        return "Trawa";
    }

    public String getImageResource() {
        return "src/main/resources/grass.png";
    }

}
