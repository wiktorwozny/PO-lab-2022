package agh.ics.oop;

import java.util.Comparator;

public class MapBoundarySetElementYcomparator implements Comparator<MapBoundarySetElement> {

    @Override
    public int compare(MapBoundarySetElement a, MapBoundarySetElement b) {

        Vector2d aPos = a.getPosition();
        Vector2d bPos = b.getPosition();

        if (aPos.y != bPos.y) {
            return aPos.y - bPos.y;
        }

        if (aPos.x != bPos.x) {
            return aPos.x - bPos.x;
        }

        if (a.getWorldMapElement() instanceof Animal) {
            return 1;
        }

        return 0;
    }

}
