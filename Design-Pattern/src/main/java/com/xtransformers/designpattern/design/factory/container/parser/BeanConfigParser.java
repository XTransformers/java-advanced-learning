package com.xtransformers.designpattern.design.factory.container.parser;

import com.xtransformers.designpattern.design.factory.container.BeanDefinition;

import java.io.InputStream;
import java.util.List;

/**
 * @author daniel
 * @date 2021-05-30
 */
public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream in);

    List<BeanDefinition> parse(String configLocation);
}
