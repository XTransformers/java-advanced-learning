package com.xtransformers.designpattern.design.factory.factory.method;

import com.xtransformers.designpattern.design.factory.config.IRuleConfigParser;
import com.xtransformers.designpattern.design.factory.config.YamlRuleConfigParser;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new YamlRuleConfigParser();
    }
}
