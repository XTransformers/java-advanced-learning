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
//        test1();
//        test2();
        test3();
    }

    private static void test1() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
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
     * 把IDEA中的代码删掉
     * Custom ClassLoader
     * Hello World Class is Initialized.
     * com.xtransformers.multithread.jvm.classloader.custom.HelloWorld@3764951d
     * Hello World
     */
    /**
     * IDEA 中代码不删掉
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * Hello World Class is Initialized.
     * com.xtransformers.multithread.jvm.classloader.custom.HelloWorld@d716361
     * Hello World
     */

    /**
     * 不删除IDEA中代码，通过编码绕过系统类加载器
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    private static void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        // 1. 直接将扩展类加载器作为自定义类加载器的父加载器
        ClassLoader extClassLoader = MainDemo.class.getClassLoader().getParent();
        CustomClassLoader classLoader = new CustomClassLoader(extClassLoader, "/Users/daniel/Documents/devTools/tmp");
        Class<?> aClass = classLoader.loadClass("com.xtransformers.multithread.jvm.classloader.custom.HelloWorld");
        // Custom ClassLoader
        System.out.println(aClass.getClassLoader());
    }

    private static void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        // 2. 指定自定义类加载器的父加载器为 null
        CustomClassLoader classLoader = new CustomClassLoader(null, "/Users/daniel/Documents/devTools/tmp");
        Class<?> aClass = classLoader.loadClass("com.xtransformers.multithread.jvm.classloader.custom.HelloWorld");
        // Custom ClassLoader
        System.out.println(aClass.getClassLoader());
    }
}
