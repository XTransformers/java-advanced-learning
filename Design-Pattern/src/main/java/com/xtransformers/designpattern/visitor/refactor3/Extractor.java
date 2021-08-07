package com.xtransformers.designpattern.visitor.refactor3;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class Extractor implements Visitor {
    @Override
    public void visit(PdfFile pdfFile) {
        System.out.println("Extract PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        System.out.println("Extract Word.");
    }
}
