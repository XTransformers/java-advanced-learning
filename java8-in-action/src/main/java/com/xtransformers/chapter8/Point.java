package com.xtransformers.chapter8;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Point {

    public static final Comparator<Point> compareByXAndY =
            Comparator.comparing(Point::getX).thenComparing(Point::getY);

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveRightBy(int x) {
        return new Point(getX() + x, getY());
    }

    public static List<Point> moveAllRightBy(List<Point> points, int x) {
        return points.stream()
                .map(point -> new Point(point.getX() + x, point.getY()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
