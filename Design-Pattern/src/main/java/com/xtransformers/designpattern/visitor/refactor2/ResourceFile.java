package com.xtransformers.designpattern.visitor.refactor2;

/**
 * @author daniel
 * @date 2021-08-07
 */
public abstract class ResourceFile {
    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract void accept(Extractor extractor);

    public abstract void accept(Compressor compressor);
}
