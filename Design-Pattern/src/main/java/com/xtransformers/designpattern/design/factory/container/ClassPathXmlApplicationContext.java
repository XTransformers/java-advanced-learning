package com.xtransformers.designpattern.design.factory.container;

import com.xtransformers.designpattern.design.factory.container.parser.BeanConfigParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private BeansFactory beansFactory;

    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beansFactory = beansFactory;
        this.beanConfigParser = beanConfigParser;
        loadBeanDefinitions(configLocation);
    }

    private void loadBeanDefinitions(String configLocation) {
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream("/" + configLocation);
            if (in == null) {
                throw new RuntimeException("can't find config file : " + configLocation);
            }
            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(in);
            beansFactory.addBeanDefinitions(beanDefinitions);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public Object getBean(String beanId) {
        return beansFactory.getBean(beanId);
    }
}
