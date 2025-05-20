/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Location;
import airport.models.storages.LocationStorage;

/**
 *
 * @author Santiago Solorzano
 */

public class LocationController {
    private final LocationStorage locationStorage;

    public LocationController() throws Exception {
        this.locationStorage = LocationStorage.getInstance();
    }
    public Response registerLocation(String airportId, String name, String city, 
                                   String country, String latitude, String longitude) {
        try {
            // Validación de campos obligatorios
            if (airportId == null || airportId.trim().isEmpty()) {
                return new Response("El ID de aeropuerto es obligatorio", Status.BAD_REQUEST);
            }

            if (name == null || name.trim().isEmpty()) {
                return new Response("El nombre del aeropuerto es obligatorio", Status.BAD_REQUEST);
            }

            if (city == null || city.trim().isEmpty()) {
                return new Response("La ciudad es obligatoria", Status.BAD_REQUEST);
            }

            if (country == null || country.trim().isEmpty()) {
                return new Response("El país es obligatorio", Status.BAD_REQUEST);
            }

            // Validación de formato de coordenadas
            double lat, lon;
            try {
                lat = Double.parseDouble(latitude);
                lon = Double.parseDouble(longitude);
            } catch (NumberFormatException e) {
                return new Response("Las coordenadas deben ser números válidos", Status.BAD_REQUEST);
            }

            // Validación de rangos geográficos
            if (lat < -90 || lat > 90) {
                return new Response("Latitud debe estar entre -90 y 90", Status.BAD_REQUEST);
            }

            if (lon < -180 || lon > 180) {
                return new Response("Longitud debe estar entre -180 y 180", Status.BAD_REQUEST);
            }

            // Validación de ID único
            if (locationStorage.getLocationById(airportId) != null) {
                return new Response("El ID de aeropuerto ya existe", Status.BAD_REQUEST);
            }

            // Crear y guardar la nueva ubicación
            Location newLocation = new Location(airportId, name, city, country, lat, lon);
            
            if (locationStorage.addLocation(newLocation)) {
                return new Response("Ubicación registrada exitosamente", Status.CREATED, newLocation);
            } else {
                return new Response("Error al guardar ubicación", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}