package com.xtransformers.chapter14.pattern;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author daniel
 * @date 2021-05-11
 */
public class PatternDemo {

    public static <T> T patternMatchExpr(Expr e,
                                         TriFunction<String, Expr, Expr, T> binopcase,
                                         Function<Integer, T> numbercase,
                                         Supplier<T> defaultcase) {
        return e instanceof BinOp ?
                binopcase.apply(((BinOp) e).getOpname(), ((BinOp) e).getLeft(),
                        ((BinOp) e).getRight()) :
                e instanceof Number ?
                        numbercase.apply(((Number) e).getVal()) :
                        defaultcase.get();
    }

    @Test
    public void test1() {
        Expr e = null;
        patternMatchExpr(e, (op, l, r) -> {
                    return 1;
                },
                (n) -> {
                    return 2;
                },
                () -> {
                    return 3;
                });
    }

    public static Expr simplify(Expr e) {
        TriFunction<String, Expr, Expr, Expr> binopcase =
                (opname, left, right) -> {
                    if ("+".equals(opname)) {
                        if (left instanceof Number && ((Number) left).getVal() == 0) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).getVal() == 0) {
                            return right;
                        }
                    }
                    if ("*".equals(opname)) {
                        if (left instanceof Number && ((Number) left).getVal() == 1) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).getVal() == 1) {
                            return right;
                        }
                    }
                    return new BinOp(opname, left, right);
                };
        Function<Integer, Expr> numbercase = val -> new Number(val);
        Supplier<Expr> defaultcase = () -> new Number(0);
        return patternMatchExpr(e, binopcase, numbercase, defaultcase);
    }

    @Test
    public void test2() {
        Expr e = new BinOp("+", new Number(5), new Number(0));
        Expr simplify = simplify(e);
        System.out.println(simplify);
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    public static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x :
                compose(f, repeat(n - 1, f));
    }

    @Test
    public void test3() {
        // 80
        int i = repeat(3, (Integer x) -> 2 * x).apply(10);
        System.out.println(i);
    }
}
