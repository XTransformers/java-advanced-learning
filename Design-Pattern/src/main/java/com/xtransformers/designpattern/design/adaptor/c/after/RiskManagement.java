package com.xtransformers.designpattern.design.adaptor.c.after;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 * @date 2021-06-08
 */
public class RiskManagement {

    private List<ISensitiveWordsFilter> filters = new ArrayList<>();

    public void addSensitiveWordsFilter(ISensitiveWordsFilter filter) {
        filters.add(filter);
    }

    public String filterSensitiveWords(String text) {
        String maskedText = text;
        for (ISensitiveWordsFilter filter : filters) {
            maskedText = filter.filter(maskedText);
        }
        return maskedText;
    }
}
