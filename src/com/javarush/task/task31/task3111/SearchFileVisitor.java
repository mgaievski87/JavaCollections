package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }



    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(attrs.isDirectory())
            return  FileVisitResult.CONTINUE;

        if(this.partOfName != null){
            if(file.getFileName().toString().indexOf(this.partOfName) == -1)
                return FileVisitResult.CONTINUE;
        }

        if(this.partOfContent != null){
            String fileContent = new String(Files.readAllBytes(file));
            if(fileContent.indexOf(this.partOfContent) == -1)
                return FileVisitResult.CONTINUE;
        }

        if(this.maxSize != 0){
            if(attrs.size() >= this.maxSize)
                return FileVisitResult.CONTINUE;
        }

        if(this.minSize != 0){
            if(attrs.size() <= this.minSize)
                return FileVisitResult.CONTINUE;
        }

        foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }
}
