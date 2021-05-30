package com.xtransformers.designpattern.design.factory;

import com.xtransformers.designpattern.design.factory.config.IRuleConfigParser;
import com.xtransformers.designpattern.design.factory.factory.method.IRuleConfigParserFactory;
import com.xtransformers.designpattern.design.factory.factory.method.RuleConfigParserFactoryMap;
import com.xtransformers.designpattern.design.factory.simple.factory.RuleConfigParserFactory;
import com.xtransformers.designpattern.design.factory.simple.factory.RuleConfigParserFactory2;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class RuleConfigSource {

    // 简单工厂
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension);
        parser = RuleConfigParserFactory2.createParser(ruleConfigFileExtension);
        if (parser == null) {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    // 工厂方法
    public RuleConfig load2(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (parserFactory == null) {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + ruleConfigFilePath);
        }

        IRuleConfigParser parser = parserFactory.createParser();

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}
