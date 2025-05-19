package airport.models.storages;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileManager {
    private static final String DATA_DIR = "data/";

    public static JSONArray readJsonArray(String filename) throws Exception {
        String filePath = DATA_DIR + filename;
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONArray(content);
    }

    public static void writeJsonArray(String filename, JSONArray jsonArray) throws Exception {
        String filePath = DATA_DIR + filename;
        Files.write(Paths.get(filePath), jsonArray.toString().getBytes());
    }
}
