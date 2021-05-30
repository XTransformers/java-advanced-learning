package com.xtransformers.designpattern.design.factory.factory.method;

import com.xtransformers.designpattern.design.factory.config.IRuleConfigParser;
import com.xtransformers.designpattern.design.factory.config.JsonRuleConfigParser;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}
