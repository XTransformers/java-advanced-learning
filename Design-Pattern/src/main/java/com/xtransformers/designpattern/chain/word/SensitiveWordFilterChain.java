package com.xtransformers.designpattern.chain.word;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author daniel
 * @date 2021-07-18
 */
public class SensitiveWordFilterChain {

    private List<SensitiveWordFilter> filters = Lists.newArrayList();

    public void addFilter(SensitiveWordFilter filter) {
        filters.add(filter);
    }

    /**
     * @param content
     * @return true if content doesn't contain sensitive words.
     */
    public boolean filter(String content) {
        for (SensitiveWordFilter filter : filters) {
            if (!filter.doFilter(content)) {
                return false;
            }
        }
        return true;
    }
}
