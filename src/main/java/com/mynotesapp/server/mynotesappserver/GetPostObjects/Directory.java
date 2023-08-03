package com.mynotesapp.server.mynotesappserver.GetPostObjects;

import java.util.List;

public class Directory <T,K>{
    private List<T> baseDirectory;
    private List<K> subDirectory;

    public List<T> getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(List<T> baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public List<K> getSubDirectory() {
        return subDirectory;
    }

    public void setSubDirectory(List<K> subDirectory) {
        this.subDirectory = subDirectory;
    }
}
