package com.xtransformers.designpattern.visitor.refactor4;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class WordExtractor implements Extractor {
    @Override
    public void extract2txt(ResourceFile resourceFile) {
        System.out.println("WordExtractor.extract2txt");
    }
}
