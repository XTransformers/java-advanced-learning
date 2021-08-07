package com.xtransformers.designpattern.visitor.refactor2;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class Extractor {

    public void extract2txt(PdfFile pdfFile) {
        System.out.println("Extract PDF.");
    }

    public void extract2txt(WordFile wordFile) {
        System.out.println("Extract Word.");
    }

}
