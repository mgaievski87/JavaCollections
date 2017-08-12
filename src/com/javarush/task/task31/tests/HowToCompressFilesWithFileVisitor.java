package com.javarush.task.task31.tests;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Maxim on 7/14/2017.
 */
public class HowToCompressFilesWithFileVisitor {
    static ZipOutputStream zipOutputStream;
    static Path directory;
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            directory = Paths.get(reader.readLine());
            zipOutputStream = new ZipOutputStream(new FileOutputStream("ArchiveThroughFileVisitor.zip"));

            Files.walkFileTree(directory, new ArchiveFileVisitor());

            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class ArchiveFileVisitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String zipEntryName = directory.relativize(file).toString();
            zipOutputStream.putNextEntry(new ZipEntry(zipEntryName));
            Files.copy(file,zipOutputStream);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            return super.preVisitDirectory(dir, attrs);
        }
    }
}
