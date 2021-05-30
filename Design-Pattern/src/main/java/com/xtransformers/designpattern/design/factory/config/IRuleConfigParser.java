package com.xtransformers.designpattern.design.factory.config;

import com.xtransformers.designpattern.design.factory.RuleConfig;

/**
 * @author daniel
 * @date 2021-05-30
 */
public interface IRuleConfigParser {
    RuleConfig parse(String configText);
}
