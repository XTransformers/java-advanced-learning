package com.xtransformers.designpattern.visitor.refactor3;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class Compressor implements Visitor {
    @Override
    public void visit(PdfFile pdfFile) {
        System.out.println("Compress PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        System.out.println("Compress Word.");
    }
}
