package agh.ics.oop;

public class Animal {

    private Vector2d position;
    private MapDirection direction;

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.direction = MapDirection.NORTH;
    }

    public String toString() {
        return "position: " + this.position.toString() + " direction: " + direction.toString();
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    void move(MoveDirection direction) {

        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> {
                Vector2d temp = this.position.add(this.direction.toUnitVector());
                if (0 <= temp.x && temp.x <= 4 && 0 <= temp.y && temp.y <= 4) {
                    this.position = temp;
                }
            }
            case BACKWARD -> {
                Vector2d temp2 = this.position.substract(this.direction.toUnitVector());
                if (0 <= temp2.x && temp2.x <= 4 && 0 <= temp2.y && temp2.y <= 4) {
                    this.position = temp2;
                }
            }
        }

    }



}
