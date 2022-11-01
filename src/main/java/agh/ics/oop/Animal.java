package agh.ics.oop;

public class Animal {

    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;
    private Vector2d previousPosition;

    Animal(IWorldMap map) {
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }


    public String toString() {
        return switch (this.direction) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
//        return "position: " + this.position.toString() + " direction: " + direction.toString();
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
