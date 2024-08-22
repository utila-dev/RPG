package kr.utila.rpg.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static final Gson DEFAULT_GSON = new GsonBuilder().disableHtmlEscaping().create();
    public static final Gson PRETTY_GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public static void write(File file, Object object) {
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Path path = Paths.get(file.getPath());
                Files.createFile(path);
            }
            String jsonContent = PRETTY_GSON.toJson(object);

            try (
                    OutputStream outputStream = Files.newOutputStream(file.toPath());
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            ) {
                bufferedWriter.write(jsonContent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object read(File file, Class<?> clazz) {
        try {
            return DEFAULT_GSON.fromJson(new FileReader(file), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object object) {
        return PRETTY_GSON.toJson(object);
    }

    public static Object toObject(String json, Class<?> clazz) {
        return DEFAULT_GSON.fromJson(json, clazz);
    }
}
