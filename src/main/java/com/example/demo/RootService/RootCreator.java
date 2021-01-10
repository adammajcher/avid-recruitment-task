package com.example.demo.RootService;

import com.example.demo.jsonObjects.Root;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RootCreator {

    private static String readLineByLineJava8(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    public static Root createRoot() throws IOException {
        String fileName = "src/main/resources/json/data.json";
        String json = readLineByLineJava8(fileName);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, Root.class);
    }
}
