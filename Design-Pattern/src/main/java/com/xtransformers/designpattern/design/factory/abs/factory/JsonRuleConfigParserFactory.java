package com.xtransformers.designpattern.design.factory.abs.factory;

import com.xtransformers.designpattern.design.factory.config.IRuleConfigParser;
import com.xtransformers.designpattern.design.factory.config.JsonRuleConfigParser;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class JsonRuleConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleConfigParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemConfigParser() {
        return new JsonSystemConfigParser();
    }
}
