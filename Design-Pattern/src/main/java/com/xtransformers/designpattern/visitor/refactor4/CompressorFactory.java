package com.xtransformers.designpattern.visitor.refactor4;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class CompressorFactory {

    private static final Map<ResourceFileType, Compressor> COMPRESSORS = Maps.newHashMap();

    static {
        COMPRESSORS.put(ResourceFileType.PDF, new PdfCompressor());
        COMPRESSORS.put(ResourceFileType.WORD, new WordCompressor());
    }

    public static Compressor getCompressor(ResourceFileType type) {
        return COMPRESSORS.get(type);
    }
}
