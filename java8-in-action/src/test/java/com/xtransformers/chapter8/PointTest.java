package com.xtransformers.chapter8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PointTest {

    @Test
    public void moveRightBy() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        Assert.assertEquals(15, p2.getX());
        Assert.assertEquals(5, p2.getY());
    }

    // 1.测试可见 Lambda 函数的行为
    @Test
    public void test() {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndY.compare(p1, p2);
        Assert.assertEquals(-1, result);
    }

    // 2.测试使用 Lambda 方法的行为
    @Test
    public void moveAllRightBy() {
        List<Point> points =
                Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints =
                Arrays.asList(new Point(15, 5), new Point(20, 5));
        List<Point> newPoints = Point.moveAllRightBy(points, 10);
        Assert.assertEquals(expectedPoints, newPoints);
    }

    // 3.将复杂 Lambda 表达式分到不同的方法
    // 4.高阶函数的测试
    @Test
    public void highOrderFunction() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> even = filter(numbers, num -> num % 2 == 0);
        List<Integer> smallerThanThree = filter(numbers, num -> num < 3);
        Assert.assertEquals(Arrays.asList(2, 4), even);
        Assert.assertEquals(Arrays.asList(1, 2), smallerThanThree);
    }

    private List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}