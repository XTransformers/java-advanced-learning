package com.xtransformers.designpattern.visitor.origin;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class PdfFile extends ResourceFile {

    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void extract2txt() {
        System.out.println("Extract PDF.");
    }
}
