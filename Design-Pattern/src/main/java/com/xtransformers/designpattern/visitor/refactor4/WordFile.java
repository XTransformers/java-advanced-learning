package com.xtransformers.designpattern.visitor.refactor4;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class WordFile extends ResourceFile {
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public ResourceFileType getType() {
        return ResourceFileType.WORD;
    }
}
