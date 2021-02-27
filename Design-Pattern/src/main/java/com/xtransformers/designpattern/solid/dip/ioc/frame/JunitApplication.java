package com.xtransformers.designpattern.solid.dip.ioc.frame;

import java.util.ArrayList;
import java.util.List;

/**
 * 把这个简化版本的测试框架引入到工程中之后
 * 我们只需要在框架预留的扩展点
 * 也就是 TestCase 类中的 doTest() 抽象函数中，填充具体的测试代码就可以实现之前的功能了，
 * 完全不需要写负责执行流程的 main() 函数了。
 */
public class JunitApplication {

    private static final List<TestCase> testCases = new ArrayList<>();

    public static final void register(TestCase testCase) {
        testCases.add(testCase);
    }

    public static final void main(String[] args) {
        for (TestCase testCase : testCases) {
            testCase.run();
        }
    }
}
