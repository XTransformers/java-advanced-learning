package com.xtransformers.designpattern.design.adaptor.c.after;

/**
 * 使用适配器模式进行改造
 * 统一接口定义
 *
 * @author daniel
 * @date 2021-06-08
 */
public interface ISensitiveWordsFilter {
    String filter(String text);
}
