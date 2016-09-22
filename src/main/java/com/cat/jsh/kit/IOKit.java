package com.cat.jsh.kit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IOKit {

    public static String getRootPath() {
        URL url = getRootUrl();
        return url == null ? null : url.toString();
    }

    public static URL getRootUrl() {
        return ClassLoader.getSystemClassLoader().getResource("");
    }

    public static Path getPath(String resource) throws Exception {
        URL url = new URL(getRootUrl(), resource);
        return Paths.get(url.toURI());
        //return Paths.get(getRootPath() + resource);//TODO
    }

    public static File createFile(String resource) throws IOException {
        //URL url = new URL(getRootPath() + resource);
        URL url = new URL(getRootUrl(), resource);
        File file = new File(url.getPath());

        if (file.exists()) {
            System.out.println("file " + file.getName() + " is already exists.");
        }
        file.createNewFile();
        return file;
    }

    public static Path createPath(String resource) throws Exception {
        Path path = getPath(resource);
        if (path == null) {
            throw new RuntimeException();
        }
        if (!Files.exists(path)) {
            path = Files.createFile(path);
            if (!Files.exists(path)) {
                throw new RuntimeException("create error.");
            }
        }
        return path;
    }

    public static void write(Path path, byte[] bytes) throws Exception {
        System.out.println(path);
        Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}
