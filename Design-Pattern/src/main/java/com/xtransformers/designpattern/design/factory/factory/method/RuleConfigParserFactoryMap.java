package com.xtransformers.designpattern.design.factory.factory.method;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 工厂方法 + 简单工厂
 *
 * @author daniel
 * @date 2021-05-30
 */
public class RuleConfigParserFactoryMap {

    private static final Map<String, IRuleConfigParserFactory> cachedFactories = Maps.newHashMap();

    static {
        cachedFactories.put("json", new JsonRuleConfigParserFactory());
        cachedFactories.put("xml", new XmlRuleConfigParserFactory());
        cachedFactories.put("yaml", new YamlRuleConfigParserFactory());
        cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
    }

    public static IRuleConfigParserFactory getParserFactory(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        return cachedFactories.get(type);
    }

}
