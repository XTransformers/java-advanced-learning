package com.xtransformers.designpattern.visitor.refactor2;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class Compressor {

    public void compress(PdfFile pdfFile) {
        System.out.println("Compress PDF.");
    }

    public void compress(WordFile wordFile) {
        System.out.println("Compress Word.");
    }

}
