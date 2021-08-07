package com.xtransformers.designpattern.visitor.refactor4;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class ExtractorFactory {

    private static final Map<ResourceFileType, Extractor> EXTRACTORS = Maps.newHashMap();

    static {
        EXTRACTORS.put(ResourceFileType.PDF, new PdfExtractor());
        EXTRACTORS.put(ResourceFileType.WORD, new WordExtractor());
    }

    public static Extractor getExtractor(ResourceFileType type) {
        return EXTRACTORS.get(type);
    }
}
