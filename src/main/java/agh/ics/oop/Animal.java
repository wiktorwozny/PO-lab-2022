package agh.ics.oop;

import static java.lang.System.out;

public class Animal extends AbstractWorldMapElement {

    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;
    private Vector2d previousPosition;

    public Animal(IWorldMap map) {
        super(new Vector2d(2, 2));
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.map = map;
    }


    public String toString() {
        return switch (this.direction) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    void move(MoveDirection direction) {

        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.direction.toUnitVector());

                if (this.map.canMoveTo(newPosition)) {
                    this.previousPosition = position;
                    this.position = newPosition;
                    this.map.place(this);
                }
            }
            case BACKWARD -> {
                Vector2d newPosition2 = this.position.substract(this.direction.toUnitVector());

                if (this.map.canMoveTo(newPosition2)) {
                    this.previousPosition = position;
                    this.position = newPosition2;
                    this.map.place(this);
                }
            }
        }

    }

    public Vector2d getPosition() {return this.position;}
    public MapDirection getDirection() {return this.direction;}
    public Vector2d getPreviousPosition() {return previousPosition;}

}
