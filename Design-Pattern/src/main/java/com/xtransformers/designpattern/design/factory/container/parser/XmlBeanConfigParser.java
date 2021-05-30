package com.xtransformers.designpattern.design.factory.container.parser;

import com.google.common.collect.Lists;
import com.xtransformers.designpattern.design.factory.container.BeanDefinition;

import java.io.InputStream;
import java.util.List;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class XmlBeanConfigParser implements BeanConfigParser {
    @Override
    public List<BeanDefinition> parse(InputStream in) {
        List<BeanDefinition> beanDefinitions = Lists.newArrayList();

        return beanDefinitions;
    }

    @Override
    public List<BeanDefinition> parse(String configLocation) {
        List<BeanDefinition> beanDefinitions = Lists.newArrayList();

        return beanDefinitions;
    }
}
