package com.xtransformers.designpattern.visitor.origin;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class WordFile extends ResourceFile {
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void extract2txt() {
        System.out.println("Extract word.");
    }
}
