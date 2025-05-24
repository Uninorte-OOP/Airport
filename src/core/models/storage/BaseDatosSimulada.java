/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Avion;
import core.models.Pasajero;
import core.models.Ubicacion;
import core.models.Vuelo;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author NICOLAS ARIAS
 */
public class BaseDatosSimulada {
    private Map<String, Ubicacion> ubicaciones = new HashMap<>();
    private Map<String, Avion> aviones = new HashMap<>();
    private Map<Long, Pasajero> pasajeros = new HashMap<>();
    private Map<String, Vuelo> vuelos = new HashMap<>();

    public void cargarDatosDesdeJSON(String rutaArchivo) throws Exception {
        String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        JSONObject json = new JSONObject(contenido);

        // Cargar ubicaciones
        JSONArray arrUbicaciones = json.getJSONArray("ubicaciones");
        for (int i = 0; i < arrUbicaciones.length(); i++) {
            JSONObject obj = arrUbicaciones.getJSONObject(i);
            Ubicacion u = new Ubicacion(
                    obj.getString("id"),
                    obj.getString("nombre"),
                    obj.getString("ciudad"),
                    obj.getString("pais"),
                    obj.getDouble("latitud"),
                    obj.getDouble("longitud")
            );
            ubicaciones.put(u.getIdAeropuerto(), u);
        }

        // Cargar aviones
        JSONArray arrAviones = json.getJSONArray("aviones");
        for (int i = 0; i < arrAviones.length(); i++) {
            JSONObject obj = arrAviones.getJSONObject(i);
            Avion a = new Avion(
                    obj.getString("id"),
                    obj.getString("marca"),
                    obj.getString("modelo"),
                    obj.getInt("capacidadMaxima"),
                    obj.getString("aerolinea")
            );
            aviones.put(a.getId(), a);
        }

        // Cargar pasajeros
        JSONArray arrPasajeros = json.getJSONArray("pasajeros");
        for (int i = 0; i < arrPasajeros.length(); i++) {
            JSONObject obj = arrPasajeros.getJSONObject(i);
            Pasajero p = new Pasajero(
                    obj.getLong("id"),
                    obj.getString("nombre"),
                    obj.getString("apellido"),
                    LocalDate.parse(obj.getString("fechaNacimiento")),
                    obj.getInt("codigoPaisTelefono"),
                    obj.getLong("telefono"),
                    obj.getString("pais")
            );
            pasajeros.put(p.getId(), p);
        }

        // Cargar vuelos y relacionar objetos
        JSONArray arrVuelos = json.getJSONArray("vuelos");
        for (int i = 0; i < arrVuelos.length(); i++) {
            JSONObject obj = arrVuelos.getJSONObject(i);
            Avion avion = aviones.get(obj.getString("idAvion"));
            Ubicacion salida = ubicaciones.get(obj.getString("idUbicacionSalida"));
            Ubicacion llegada = ubicaciones.get(obj.getString("idUbicacionLlegada"));
            LocalDateTime fechaSalida = LocalDateTime.parse(obj.getString("fechaSalida"));
            int durH = obj.getInt("duracionHorasLlegada");
            int durM = obj.getInt("duracionMinutosLlegada");

            Vuelo vuelo = new Vuelo(
                    obj.getString("id"),
                    avion,
                    salida,
                    llegada,
                    fechaSalida,
                    durH,
                    durM
            );

            // Agregar pasajeros al vuelo y al vuelo al pasajero
            JSONArray arrPasajerosVuelo = obj.getJSONArray("pasajeros");
            for (int j = 0; j < arrPasajerosVuelo.length(); j++) {
                long idPasajero = arrPasajerosVuelo.getLong(j);
                Pasajero pasajero = pasajeros.get(idPasajero);
                if (pasajero != null) {
                    vuelo.agregarPasajero(pasajero);
                    pasajero.agregarVuelo(vuelo);
                }
            }

            vuelos.put(vuelo.getId(), vuelo);
            // Asociar vuelo a avión también
            avion.agregarVuelo(vuelo);
        }
    }

    // Métodos para obtener datos
    public Vuelo obtenerVuelo(String id) {
        return vuelos.get(id);
    }

    public Pasajero obtenerPasajero(long id) {
        return pasajeros.get(id);
    }

    public Avion obtenerAvion(String id) {
        return aviones.get(id);
    }

    public Ubicacion obtenerUbicacion(String id) {
        return ubicaciones.get(id);
    }
}

