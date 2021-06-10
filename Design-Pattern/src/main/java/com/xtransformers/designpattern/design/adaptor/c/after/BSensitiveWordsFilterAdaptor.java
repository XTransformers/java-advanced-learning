package com.xtransformers.designpattern.design.adaptor.c.after;

import com.xtransformers.designpattern.design.adaptor.c.BSensitiveWordsFilter;

/**
 * @author daniel
 * @date 2021-06-08
 */
public class BSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private BSensitiveWordsFilter bFilter;

    @Override
    public String filter(String text) {
        return bFilter.filter(text);
    }
}
