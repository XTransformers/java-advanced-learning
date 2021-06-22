package com.xtransformers.designpattern.compose.before;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 * @date 2021-06-22
 */
public class FileSystemNode {

    private String path;
    private boolean isFile;
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

    public int countNumOfFiles() {
        if (isFile) {
            return 1;
        }
        int numOfFiles = 0;
        for (FileSystemNode subNode : subNodes) {
            numOfFiles += subNode.countNumOfFiles();
        }
        return numOfFiles;
    }

    public long countSizeOfFiles() {
        if (isFile) {
            File file = new File(path);
            if (!file.exists()) {
                return 0;
            }
            return file.length();
        }
        long sizeOfFiles = 0;
        for (FileSystemNode subNode : subNodes) {
            sizeOfFiles += subNode.countSizeOfFiles();
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

    public String getPath() {
        return path;
    }
}
