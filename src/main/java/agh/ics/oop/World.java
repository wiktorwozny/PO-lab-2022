package agh.ics.oop;
import static java.lang.System.out;

public class World {

    public static void main(String[] args) {

        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions = parser.parse(args);

        Animal zwierzak = new Animal();
        for (MoveDirection direction: directions) {
            out.println(zwierzak);
            zwierzak.move(direction);
        }
    }

    private static MoveDirection[] createArray(String[] args) {

        int n = 0;

        for (String string: args) {
            if (string.equals("f") || string.equals("r") || string.equals("l") || string.equals("b")) {
                n++;
            }
        }

        MoveDirection[] enums = new MoveDirection[n];

        int i = 0;
        for (String string: args) {
            switch (string) {
                case "f" -> {
                    enums[i] = MoveDirection.FORWARD;
                    i++;
                }
                case "r" -> {
                    enums[i] = MoveDirection.RIGHT;
                    i++;
                }
                case "l" -> {
                    enums[i] = MoveDirection.LEFT;
                    i++;
                }
                case "b" -> {
                    enums[i] = MoveDirection.BACKWARD;
                    i++;
                }
            }
        }
        return enums;
    }

    private static void run(MoveDirection[] enums) {

        for(MoveDirection argument: enums) {
            switch (argument) {
                case FORWARD -> out.println("Zwierzak idzie do przodu,");
                case RIGHT -> out.println("Zwierzak skręca w prawo,");
                case LEFT -> out.println("Zwierzak skręca w lewo,");
                case BACKWARD -> out.println("Zwierzak idzie do tyłu,");
            }
        }
    }
}
