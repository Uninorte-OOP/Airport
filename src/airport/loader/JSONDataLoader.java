package airport.loader;

import airport.Flight;
import airport.Location;
import airport.Passenger;
import airport.Plane;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JSONDataLoader {

    public static List<Passenger> loadPassengers(String filePath) throws IOException {
        List<Passenger> passengers = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String birthDateStr = obj.getString("birthDate");  // Ejemplo: "1990-04-15"
                String[] parts = birthDateStr.split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                LocalDate birthDate = LocalDate.of(year, month, day);
                Passenger passenger = new Passenger(
                        obj.getLong("id"),
                        obj.getString("firstname"),
                        obj.getString("lastname"),
                        birthDate,
                        obj.getInt("countryPhoneCode"),
                        obj.getLong("phone"),
                        obj.getString("country")
                );
                passengers.add(passenger);
            }
        }
        return passengers;
    }

    public static List<Plane> loadPlanes(String filePath) throws IOException {
        List<Plane> planes = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Plane plane = new Plane(
                        obj.getString("id"),
                        obj.getString("brand"),
                        obj.getString("model"),
                        obj.getInt("maxCapacity"),
                        obj.getString("airline")
                );
                planes.add(plane);
            }
        }
        return planes;
    }

    public static List<Location> loadLocations(String filePath) throws IOException {
        List<Location> locations = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Location location = new Location(
                        obj.getString("airportId"),
                        obj.getString("airportName"),
                        obj.getString("airportCity"),
                        obj.getString("airportCountry"),
                        obj.getDouble("airportLatitude"),
                        obj.getDouble("airportLongitude")
                );
                locations.add(location);
            }
        }
        return locations;
    }

    public static List<Flight> loadFlights(String filePath) throws IOException {
        List<Flight> flights = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String scaleLocation = obj.isNull("scaleLocation") ? null : obj.getString("scaleLocation");
                Plane plane = new Plane(
                        obj.getString("plane"),
                        "",
                        "",
                        1,"");
                Location location1 = new Location(obj.getString("departureLocation"),"","","",12.2,12.2);
                Location location2 = new Location(obj.getString("arrivalLocation"),"","","",12.2,12.2);
                Location location3 = new Location(scaleLocation,"","","",12.2,12.2);
                
                String departureDateStr = obj.getString("departureDate");  // Ejemplo: "2025-06-01T14:30:00"
                String[] dateTimeParts = departureDateStr.split("T");
                String[] dateParts = dateTimeParts[0].split("-");
                String[] timeParts = dateTimeParts[1].split(":");

                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);
                int hour = Integer.parseInt(timeParts[0]);
                int minute = Integer.parseInt(timeParts[1]);

                LocalDateTime departureDate = LocalDateTime.of(year, month, day, hour, minute);

                
                Flight flight = new Flight(
                        obj.getString("id"),
                        plane,
                        location1,
                        location3,
                        location2,
                        departureDate,
                        obj.getInt("hoursDurationArrival"),
                        obj.getInt("minutesDurationArrival"),
                        obj.getInt("hoursDurationScale"),
                        obj.getInt("minutesDurationScale")
                );
                flights.add(flight);
            }
        }
        return flights;
    }
}
