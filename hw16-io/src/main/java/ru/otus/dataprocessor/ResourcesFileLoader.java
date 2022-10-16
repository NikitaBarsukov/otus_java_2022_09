package ru.otus.dataprocessor;

import jakarta.json.Json;
import jakarta.json.JsonValue;
import ru.otus.model.Measurement;

import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        List<Measurement> measurements;
        try (var jsonReader = Json.createReader(ResourcesFileLoader.class.getClassLoader().getResourceAsStream("inputData.json"))) {
            measurements = jsonReader.readArray().getValuesAs(JsonValue::asJsonObject).stream()
                    .map(x-> new Measurement(x.getString("name"), x.getJsonNumber("value").doubleValue()))
                    .toList();
        }
        return measurements;
    }
}
