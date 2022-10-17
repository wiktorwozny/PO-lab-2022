package agh.ics.oop;

public enum MapDirection {

    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {

        String toReturn = switch (this) {
            case NORTH -> "Północ";
            case EAST -> "Wschód";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
        };
        return toReturn;
    }

    MapDirection next() {

        MapDirection nextDirection = switch (this) {
            case NORTH -> MapDirection.EAST;
            case EAST -> MapDirection.SOUTH;
            case SOUTH -> MapDirection.WEST;
            case WEST -> MapDirection.NORTH;
        };
        return nextDirection;
    }

    MapDirection previous() {

        MapDirection prevDirection = switch (this) {
            case NORTH -> MapDirection.WEST;
            case EAST -> MapDirection.NORTH;
            case SOUTH -> MapDirection.EAST;
            case WEST -> MapDirection.SOUTH;
        };
        return prevDirection;
    }

    Vector2d toUnitVector() {

        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
        };
    }

}
