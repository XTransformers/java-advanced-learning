package com.xtransformers.designpattern.visitor.refactor4;

/**
 * @author daniel
 * @date 2021-08-07
 */
public abstract class ResourceFile {

    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract ResourceFileType getType();
}
