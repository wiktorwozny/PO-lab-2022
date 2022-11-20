package agh.ics.oop;

import java.util.Comparator;

public class MapBoundarySetElementXcomparator implements Comparator<MapBoundarySetElement> {

    @Override
    public int compare(MapBoundarySetElement a, MapBoundarySetElement b) {

        Vector2d aPos = a.getPosition();
        Vector2d bPos = b.getPosition();

        if (aPos.x != bPos.x) {
            return aPos.x - bPos.x;
        }

        if (aPos.y != bPos.y) {
            return aPos.y - bPos.y;
        }

        if (a.getWorldMapElement() instanceof Animal && !(b.getWorldMapElement() instanceof Animal)) {
            return 1;
        }

        return 0;
    }

}
