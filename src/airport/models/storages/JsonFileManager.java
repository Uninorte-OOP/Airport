package airport.models.storages;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileManager {
    // Usamos Paths.get() para construir rutas multiplataforma
    private static final Path JSON_DIR = Paths.get(System.getProperty("user.dir"), "json");
    
    public static JSONArray readJsonArray(String filename) throws Exception {
        Path filePath = JSON_DIR.resolve(filename);
        if (!Files.exists(filePath)) {
            throw new RuntimeException("Archivo no encontrado: " + filePath.toAbsolutePath());
        }
        String content = Files.readString(filePath);
        return new JSONArray(content);
    }
    
    public static void writeJsonArray(String filename, JSONArray jsonArray) throws Exception {
        Path filePath = JSON_DIR.resolve(filename);
        Files.createDirectories(filePath.getParent());
        Files.writeString(filePath, jsonArray.toString());
    }
}
