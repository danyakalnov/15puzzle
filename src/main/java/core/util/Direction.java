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

    public Direction getOpposite() {
        return opposite;
    }

    @Override
    public String toString() {
        return "Direction." + this.name();
    }
}