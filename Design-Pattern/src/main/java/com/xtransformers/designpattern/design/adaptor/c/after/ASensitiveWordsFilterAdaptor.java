package com.xtransformers.designpattern.design.adaptor.c.after;

import com.xtransformers.designpattern.design.adaptor.c.ASensitiveWordsFilter;

/**
 * @author daniel
 * @date 2021-06-08
 */
public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private ASensitiveWordsFilter aFilter;

    @Override
    public String filter(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        return maskedText;
    }
}
