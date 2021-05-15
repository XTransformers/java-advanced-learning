package com.xtransformers.chapter14;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.DoubleUnaryOperator;

/**
 * 科里化
 *
 * @author daniel
 * @date 2021-05-05
 */
public class CurryingDemo {

    /**
     * @param x 被转换的量
     * @param f 转换因子
     * @param b 基线
     * @return 转换后的值
     */
    public static double converter(double x, double f, double b) {
        return x * f + b;
    }

    public static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }

    @Test
    public void test() {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        double gbp = convertUSDtoGBP.applyAsDouble(1000);


    }

    @Test
    public void main() {
        double i = ((int) 3.3473982743892 * 100) / 100.00;
        System.out.println(i);
        BigDecimal b = new BigDecimal(243.43298723489);
        b = b.setScale(3, RoundingMode.UP);
        System.out.println(b.doubleValue());
    }

}
