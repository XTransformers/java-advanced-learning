package com.xtransformers.designpattern.visitor.refactor4;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class PdfCompressor implements Compressor {
    @Override
    public void compress(ResourceFile resourceFile) {
        System.out.println("PdfCompressor.compress");
    }
}
