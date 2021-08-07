package com.xtransformers.designpattern.visitor.refactor3;

/**
 * @author daniel
 * @date 2021-08-07
 */
public interface Visitor {

    void visit(PdfFile pdfFile);

    void visit(WordFile wordFile);

}
