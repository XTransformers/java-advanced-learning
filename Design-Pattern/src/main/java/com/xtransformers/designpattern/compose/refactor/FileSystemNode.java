package com.xtransformers.designpattern.compose.refactor;

/**
 * @author daniel
 * @date 2021-06-22
 */
public abstract class FileSystemNode {

    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    public abstract int countNumOfFiles();

    public abstract long countSizeOfFiles();

    public String getPath() {
        return path;
    }
}
