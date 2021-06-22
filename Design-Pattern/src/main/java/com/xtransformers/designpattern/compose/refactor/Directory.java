package com.xtransformers.designpattern.compose.refactor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 * @date 2021-06-22
 */
public class Directory extends FileSystemNode {

    private List<FileSystemNode> subNodes = new ArrayList<>();

    public Directory(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        int numOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            numOfFiles += fileOrDir.countNumOfFiles();
        }
        return numOfFiles;
    }

    @Override
    public long countSizeOfFiles() {
        long sizeOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            sizeOfFiles += fileOrDir.countSizeOfFiles();
        }
        return sizeOfFiles;
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        if (fileOrDir != null) {
            subNodes.add(fileOrDir);
        }
    }

    public void removeSubNode(FileSystemNode fileOrDir) {
        int size = subNodes.size();
        int i = 0;
        for (; i < size; i++) {
            if (subNodes.get(i).getPath().equalsIgnoreCase(fileOrDir.getPath())) {
                break;
            }
        }
        if (i < size) {
            subNodes.remove(i);
        }
    }
}
