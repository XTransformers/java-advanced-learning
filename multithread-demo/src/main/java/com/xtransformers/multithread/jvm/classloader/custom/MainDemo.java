package com.xtransformers.multithread.jvm.classloader.custom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author daniel
 * @date 2021-12-02
 */
public class MainDemo {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        CustomClassLoader classLoader = new CustomClassLoader("/Users/daniel/Documents/devTools/tmp");
        Class<?> aClass = classLoader.loadClass("com.xtransformers.multithread.jvm.classloader.custom.HelloWorld");
        System.out.println(aClass.getClassLoader());

        // 以下部分代码注释掉，不会输出 Hello World Class is Initialized.
        // 因为使用类加载器 loadClass 不会导致类的主动初始化，只是执行了加载过程中的加载阶段而已。
        Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);
        Method welcomeMethod = aClass.getMethod("welcome");
        String result = (String) welcomeMethod.invoke(helloWorld);
        System.out.println(result);
    }
    /**
     * Custom ClassLoader
     * Hello World Class is Initialized.
     * com.xtransformers.multithread.jvm.classloader.custom.HelloWorld@3764951d
     * Hello World
     */
}
