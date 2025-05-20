
package airport.models.storages;

import airport.models.Plane;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PlaneStorage {
    private static PlaneStorage instance;
    private List<Plane> planes;
    private static final String FILENAME = "planes.json";

    private PlaneStorage() throws Exception {
        this.planes = loadFromJson();
    }

    public static PlaneStorage getInstance() throws Exception {
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }

    private List<Plane> loadFromJson() throws Exception {
        List<Plane> planes = new ArrayList<>();
        JSONArray jsonArray = JsonFileManager.readJsonArray(FILENAME);
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Plane plane = new Plane(
                json.getString("id"),
                json.getString("brand"),
                json.getString("model"),
                json.getInt("maxCapacity"),
                json.getString("airline")
            );
            planes.add(plane);
        }
        return planes;
    }

    public Plane getPlaneById(String id) {
        return planes.stream()
            .filter(plane -> plane.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public List<Plane> getAllPlanes() {
        return new ArrayList<>(planes);
    }

    public boolean addPlane(Plane newPlane) {
        try {
            // Verificación adicional
            if (planes.stream().anyMatch(p -> p.getId().equals(newPlane.getId()))) {
                System.err.println("Error: ID de avión duplicado detectado en Storage");
                return false;
            }
            
            planes.add(newPlane);
            System.out.println("Avión añadido (sin persistencia JSON aún): " + newPlane.getId());
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al añadir avión: " + e.getMessage());
            return false;
        }
    }
}
