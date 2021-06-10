package com.xtransformers.designpattern.design.adaptor.c.after;

import com.xtransformers.designpattern.design.adaptor.c.CSensitiveWordsFilter;

/**
 * @author daniel
 * @date 2021-06-08
 */
public class CSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private CSensitiveWordsFilter cFilter;

    @Override
    public String filter(String text) {
        return cFilter.filter(text, "");
    }
}
