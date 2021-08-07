package com.xtransformers.designpattern.visitor.origin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class ToolApplication {

    public static void main(String[] args) {
        List<ResourceFile> resourceFileList = listAllResourceFiles(args[0]);
        for (ResourceFile resourceFile : resourceFileList) {
            resourceFile.extract2txt();
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        return resourceFiles;
    }
}
