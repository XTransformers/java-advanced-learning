package com.xtransformers.designpattern.visitor.refactor2;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class WordFile extends ResourceFile {
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.extract2txt(this);
    }
}
