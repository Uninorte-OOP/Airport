
package core.models.storage;

import core.models.Passenger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class StoragePassenger {
    private static StoragePassenger instance;
    private final String ruta = "C://Users//CRISTIAN VILORIA//OneDrive//Documents//NetBeansProjects//Parcial3POO//Airport//json";
    
    private StoragePassenger() {
        
    }
    
    
    public static StoragePassenger getInstance() {
        if(instance == null){
            instance = new StoragePassenger();
        }
        return instance;
    }
    
    public JSONArray readJSONFile(String filename) {
        try {
            String path = ruta + filename;
            String content = new String(Files.readAllBytes(Paths.get(path)));
            return new JSONArray(content);
        } catch (Exception e) {
            System.err.println("Error reading " + filename + ": " + e.getMessage());
            return new JSONArray(); // Devuelve un array vacío si hay error
        }
    }
    
    public List<Passenger> getPassengers() {
        JSONArray jsonArray = readJSONFile("passengers.json");
        List<Passenger> passengers = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Passenger passenger = new Passenger(
                json.getLong("id"),
                json.getString("firstname"),
                json.getString("lastname"),
                LocalDate.parse(json.getString("birthDate")),
                json.getInt("countryPhoneCode"),
                json.getLong("phone"),
                json.getString("country")
            );
            passengers.add(passenger);
        }
        return passengers;
    }
}
