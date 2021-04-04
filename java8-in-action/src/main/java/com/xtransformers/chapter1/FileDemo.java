package com.xtransformers.chapter1;

import java.io.File;
import java.io.FileFilter;

/**
 * 方法引用示例
 */
public class FileDemo {

    public File[] getHiddenFiles(String pathName) {
        File[] hiddenFiles = new File(pathName).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });
        return hiddenFiles;
    }

    public File[] getHiddenFilesByLambda(String pathName) {
        return new File(pathName).listFiles(File::isHidden);
    }

}
