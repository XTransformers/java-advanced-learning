package com.xtransformers.designpattern.design.factory.abs.factory;

import com.xtransformers.designpattern.design.factory.config.IRuleConfigParser;

/**
 * @author daniel
 * @date 2021-05-30
 */
public interface IConfigParserFactory {
    IRuleConfigParser createRuleConfigParser();

    ISystemConfigParser createSystemConfigParser();
}
