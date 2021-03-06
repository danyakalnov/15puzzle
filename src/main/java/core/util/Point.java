package core.util;

import java.util.Objects;

public final class Point {
    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    private final int _x;
    private final int _y;

    public Point(int x, int y) {
        this._x = x;
        this._y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return _x == point._x &&
                _y == point._y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y);
    }

    @Override
    public String toString() {
        return "(" +
                "x = " + _x +
                ", y = " + _y +
                ')';
    }
}

