package com.xtransformers.chapter10.example;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class OptionalDemo {

    // 用 Optional 封装可能为 null 的值
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        Object value = map.get("key");
        Optional<Object> optValue = Optional.ofNullable(map.get("key"));
    }

    // 如果无法返回某个值，除了返回 null 之外，还可以抛异常
    public void test2() {
        int awk = Integer.parseInt("awk");
    }

    // Optional 工具类
    static class OptionalUtility {
        public static Optional<Integer> stringToInt(String s) {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
    }

    // Optional 基础类型 不建议使用
    // 最多包含一个值，与 Stream 不一样
    // 不能享受 Optional 最有用的方法，如 map/flatMap/filter 等
    public void test3() {
//        OptionalInt
//        OptionalLong
//        OptionalDouble
    }

    public void test4() {
        Properties props = new Properties();
        props.put("a", "5");
        props.put("b", "true");
        props.put("c", "-1");

        OptionalDemo optionalDemo = new OptionalDemo();
        Assert.assertEquals(5, optionalDemo.readDuration(props, "a"));
        Assert.assertEquals(0, optionalDemo.readDuration(props, "b"));
        Assert.assertEquals(0, optionalDemo.readDuration(props, "c"));
        Assert.assertEquals(0, optionalDemo.readDuration(props, "d"));
    }

    // 命令式编程
    public int readDuration(Properties properties, String name) {
        String value = properties.getProperty(name);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    // 自己实现的
    public int readDurationOptional(Properties properties, String name) {
        String value = properties.getProperty(name);
        return OptionalUtility.stringToInt(Optional.ofNullable(value).orElse(""))
                .orElse(0);
    }

    // 优雅的实现
    public int readDurationOptional1(Properties properties, String name) {
        return Optional.ofNullable(properties.getProperty(name))
                .flatMap(OptionalUtility::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

}
