package com.xtransformers.designpattern.design.adaptor.c.before;

import com.xtransformers.designpattern.design.adaptor.c.ASensitiveWordsFilter;
import com.xtransformers.designpattern.design.adaptor.c.BSensitiveWordsFilter;
import com.xtransformers.designpattern.design.adaptor.c.CSensitiveWordsFilter;

/**
 * @author daniel
 * @date 2021-06-08
 */
public class RiskManagement {
    private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
    private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();
    private CSensitiveWordsFilter cFilter = new CSensitiveWordsFilter();

    public String filterSensitiveWords(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        maskedText = bFilter.filter(maskedText);
        maskedText = cFilter.filter(maskedText, "***");
        return maskedText;
    }
}
