package com.xtransformers.designpattern.design.factory.simple.factory;

import com.google.common.collect.Maps;
import com.xtransformers.designpattern.design.factory.config.*;

import java.util.Map;

/**
 * 简单工厂模式 实现2
 *
 * @author daniel
 * @date 2021-05-30
 */
public class RuleConfigParserFactory2 {
    private static final Map<String, IRuleConfigParser> cacheParsers = Maps.newHashMap();

    static {
        cacheParsers.put("json", new JsonRuleConfigParser());
        cacheParsers.put("xml", new XmlRuleConfigParser());
        cacheParsers.put("yaml", new YamlRuleConfigParser());
        cacheParsers.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String ruleConfigFileExtension) {
        if (ruleConfigFileExtension == null || ruleConfigFileExtension.isEmpty()) {
            return null;
//            throw new InvalidRuleConfigException("");
        }

        return cacheParsers.get(ruleConfigFileExtension.toLowerCase());
    }
}
