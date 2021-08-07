package com.xtransformers.designpattern.visitor.refactor4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class ToolApplication {

    public static void main(String[] args) {
        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);
        for (ResourceFile resourceFile : resourceFiles) {
            Extractor extractor = ExtractorFactory.getExtractor(resourceFile.getType());
            extractor.extract2txt(resourceFile);

            Compressor compressor = CompressorFactory.getCompressor(resourceFile.getType());
            compressor.compress(resourceFile);
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        return resourceFiles;
    }
}
