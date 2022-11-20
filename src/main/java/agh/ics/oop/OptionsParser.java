package agh.ics.oop;

public class OptionsParser {

    public MoveDirection[] parse(String[] args) {

        int n = 0, i = 0;
        String[] strings = {"f", "forward", "r", "right", "l", "left", "b", "backward"};

        for (String string: args) {
            for (String toCompare: strings) {
                if (string.equals(toCompare)) {
                    n++;
                    break;
                }
            }
        }

        MoveDirection[] toReturn = new MoveDirection[n];
        for (String string: args) {
            switch (string) {
                case "f", "forward" -> {
                    toReturn[i] = MoveDirection.FORWARD;
                    i++;
                }
                case "r", "right" -> {
                    toReturn[i] = MoveDirection.RIGHT;
                    i++;
                }
                case "l", "left" -> {
                    toReturn[i] = MoveDirection.LEFT;
                    i++;
                }
                case "b", "backward" -> {
                    toReturn[i] = MoveDirection.BACKWARD;
                    i++;
                }
                default -> throw new IllegalArgumentException(string + " is not legal move specification");
            }
        }
        return toReturn;

    }

}
