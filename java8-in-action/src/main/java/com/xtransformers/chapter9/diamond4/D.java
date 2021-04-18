package com.xtransformers.chapter9.diamond4;

public class D implements B, C {
    public static void main(String[] args) {
        new D().hello();
    }

    // 1. A.hello
    // 变种2. 接口 B 也定义跟 A 中一样的 hello 默认方法，打印 B.hello
    // 变种3. 接口 B/C 也定义跟 A 中一样的 hello 默认方法，编译错误，必须在 D 中显式声明 hello 方法
    // 变种4. 接口 C 中定义跟 A 中一样的 hello 抽象方法，类 D 中必须显式声明 hello 方法
}
