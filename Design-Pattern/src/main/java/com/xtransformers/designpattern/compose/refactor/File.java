package com.xtransformers.designpattern.compose.refactor;

/**
 * @author daniel
 * @date 2021-06-22
 */
public class File extends FileSystemNode {
    public File(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            return 0;
        }
        return file.length();
    }
}
