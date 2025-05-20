/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Flight;
import airport.models.Location;
import airport.models.Plane;
import airport.models.storages.FlightStorage;
import airport.models.storages.LocationStorage;
import airport.models.storages.PlaneStorage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Santiago Solorzano
 */
public class FlightController {

    private final FlightStorage flightStorage;
    private final PlaneStorage planeStorage;
    private final LocationStorage locationStorage;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public FlightController() throws Exception {
        this.flightStorage = FlightStorage.getInstance();
        this.planeStorage = PlaneStorage.getInstance();
        this.locationStorage = LocationStorage.getInstance();
    }

    public Response registerFlight(
            String flightId,
            String planeId,
            String departureLocId,
            String arrivalLocId,
            String scaleLocId,
            String year,
            String month,
            String day,
            String hour,
            String minute,
            String hoursArrival,
            String minsArrival,
            String hoursScale,
            String minsScale
    ) {
        try {
            // Validación de campos obligatorios
            if (flightId == null || flightId.trim().isEmpty()) {
                return new Response("El ID de vuelo es obligatorio", Status.BAD_REQUEST);
            }

            if (planeId == null || planeId.trim().isEmpty()) {
                return new Response("Debe seleccionar un avión", Status.BAD_REQUEST);
            }

            if (departureLocId == null || departureLocId.trim().isEmpty()) {
                return new Response("Debe seleccionar ubicación de salida", Status.BAD_REQUEST);
            }

            if (arrivalLocId == null || arrivalLocId.trim().isEmpty()) {
                return new Response("Debe seleccionar ubicación de llegada", Status.BAD_REQUEST);
            }

            // Validación de fechas y horas
            LocalDateTime departureDate;
            try {
                int y = Integer.parseInt(year);
                int m = Integer.parseInt(month);
                int d = Integer.parseInt(day);
                int h = Integer.parseInt(hour);
                int min = Integer.parseInt(minute);

                departureDate = LocalDateTime.of(y, m, d, h, min);

                if (departureDate.isBefore(LocalDateTime.now())) {
                    return new Response("La fecha de salida no puede ser en el pasado", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Fecha u hora inválidas", Status.BAD_REQUEST);
            }

            // Validación de duraciones
            int hoursArr, minsArr, hoursSc = 0, minsSc = 0;
            try {
                hoursArr = Integer.parseInt(hoursArrival);
                minsArr = Integer.parseInt(minsArrival);

                if (hoursArr < 0 || minsArr < 0 || minsArr >= 60) {
                    return new Response("Duración de vuelo inválida", Status.BAD_REQUEST);
                }

                if (scaleLocId != null && !scaleLocId.trim().isEmpty()) {
                    hoursSc = Integer.parseInt(hoursScale);
                    minsSc = Integer.parseInt(minsScale);

                    if (hoursSc < 0 || minsSc < 0 || minsSc >= 60) {
                        return new Response("Duración de escala inválida", Status.BAD_REQUEST);
                    }
                }
            } catch (NumberFormatException e) {
                return new Response("Las duraciones deben ser números válidos", Status.BAD_REQUEST);
            }

            // Obtener objetos completos
            Plane plane = planeStorage.getPlaneById(planeId);
            if (plane == null) {
                return new Response("Avión no encontrado", Status.NOT_FOUND);
            }

            Location departure = locationStorage.getLocationById(departureLocId);
            Location arrival = locationStorage.getLocationById(arrivalLocId);

            if (departure == null || arrival == null) {
                return new Response("Ubicación no encontrada", Status.NOT_FOUND);
            }

            // Validación de unicidad de ID
            if (flightStorage.getFlightById(flightId) != null) {
                return new Response("El ID de vuelo ya existe", Status.BAD_REQUEST);
            }

            // Crear el vuelo
            Flight newFlight;
            if (scaleLocId == null || scaleLocId.trim().isEmpty()) {
                newFlight = new Flight(
                        flightId, plane, departure, arrival,
                        departureDate, hoursArr, minsArr
                );
            } else {
                Location scale = locationStorage.getLocationById(scaleLocId);
                if (scale == null) {
                    return new Response("Ubicación de escala no encontrada", Status.NOT_FOUND);
                }

                newFlight = new Flight(
                        flightId, plane, departure, scale, arrival,
                        departureDate, hoursArr, minsArr, hoursSc, minsSc
                );
            }

            // Guardar el vuelo
            if (flightStorage.addFlight(newFlight)) {
                return new Response("Vuelo registrado exitosamente", Status.CREATED, newFlight);
            } else {
                return new Response("Error al guardar el vuelo", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
