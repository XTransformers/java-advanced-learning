package com.xtransformers.chapter3.example;

import com.xtransformers.chapter1.domain.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class CompositeLambdaDemo {

    private List<Apple> inventory = Arrays.asList(
            new Apple(150, "green"),
            new Apple(140, "blue"),
            new Apple(160, "red")
    );

    // 比较器复合
    public void test1() {
        Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);
        inventory.sort(comparator);
        // 逆序
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        // 比较器链
        // 先按重量递减，如果重量相同，按照颜色递增
        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
    }

    // 谓词复合
    public void test2() {
        Predicate<Apple> redApple = apple -> "red".equals(apple.getColor());
        // 不是红苹果
        Predicate<Apple> negateRedApple = redApple.negate();

        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);

        // 表达式链中的位置决定优先级的

        // (red && heavy) || green
        Predicate<Apple> redAndHeavyOrGreenApple = redApple.and(apple -> apple.getWeight() > 150)
                .or(apple -> "green".equals(apple.getColor()));

        // (red || green) && heavy
        Predicate<Apple> redOrGreenAppleAndHeavy = redApple.or(apple -> "green".equals(apple.getColor()))
                .and(apple -> apple.getWeight() > 150);
    }

    // 函数复合
    public void test3() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        // h(x) = g(f(x))
        Function<Integer, Integer> h = f.andThen(g);
        // 4
        Integer result = h.apply(1);

        // h(x) = f(g(x))
        h = f.compose(g);
        // 3
        result = h.apply(1);
    }

    // 函数复合实际应用场景
    public void test4() {
        // 创建一个流水线：先加上抬头，然后进行拼写检查，最后加上一个落款，
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        // 第二个流水线可能只加抬头、落款，而不做拼写检查
        Function<String, String> anotherPipeline = addHeader.andThen(Letter::addFooter);
    }

    // 计算直线与 x 轴面积
    public void test5() {
        // 60
        double area = integrate(x -> x + 10, 3, 7);
    }

    private double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

}
