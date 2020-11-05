package core.util;
import java.util.Collections;
import java.util.List;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
    }

    private Direction opposite;

    public static List<Direction> all() {
        return Collections.unmodifiableList(List.of(NORTH, SOUTH, EAST, WEST));
    }

    public Direction getOpposite() {
        return opposite;
    }

    @Override
    public String toString() {
        return "Direction." + this.name();
    }
}