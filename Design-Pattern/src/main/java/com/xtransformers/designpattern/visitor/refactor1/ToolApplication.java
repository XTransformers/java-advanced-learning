package com.xtransformers.designpattern.visitor.refactor1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 * @date 2021-08-07
 */
public class ToolApplication {

    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);
        for (ResourceFile resourceFile : resourceFiles) {
            // Cannot resolve method 'extract2txt(com.xtransformers.designpattern.visitor.refactor1.ResourceFile)'
            // 多态是一种动态绑定，可以在运行时获取对象的实际类型，来运行实际类型对应的方法。
            // 而函数重载是一种静态绑定，在编译时并不能获取对象的实际类型，而是根据声明类型执行声明类型对应的方法。
            // extractor.extract2txt(resourceFile);
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        return resourceFiles;
    }
}
