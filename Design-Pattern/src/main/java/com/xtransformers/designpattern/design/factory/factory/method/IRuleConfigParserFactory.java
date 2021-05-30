package com.xtransformers.designpattern.design.factory.factory.method;

import com.xtransformers.designpattern.design.factory.config.IRuleConfigParser;

/**
 * @author daniel
 * @date 2021-05-30
 */
public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
}
