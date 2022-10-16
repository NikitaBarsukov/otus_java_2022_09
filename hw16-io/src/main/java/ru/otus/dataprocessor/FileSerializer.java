package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        try {
            try (OutputStream os = new PrintStream(Files.createFile(Paths.get(fileName)).toFile())) {
                os.write(new ObjectMapper().writeValueAsString(data).getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
