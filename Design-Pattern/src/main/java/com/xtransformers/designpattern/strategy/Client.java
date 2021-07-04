package com.xtransformers.designpattern.strategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author daniel
 * @date 2021-07-04
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 运行时动态确定
        Properties prop = new Properties();
        prop.load(new FileInputStream("./config.properties"));
        String type = prop.getProperty("type");
        Strategy strategy = StrategyFactory.getStrategy(type);

        // 非运行时动态确定
        type = "A";
        strategy = StrategyFactory.getStrategy(type);
    }
}
