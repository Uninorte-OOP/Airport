/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import java.util.ArrayList;

/**
 *
 * @author NICOLAS ARIAS
 */
public class AeropuertoController {
    private final ServicioAeropuertos servicio;

    public AeropuertoController(ServicioAeropuertos servicio) {
        this.servicio = servicio;
    }

    public Response<Aeropuerto> crearAeropuerto(Aeropuerto aeropuerto) {
        if (!validarAeropuerto(aeropuerto)) {
            return new Response<>(400, "Datos inválidos", null);
        }
        if (servicio.existe(aeropuerto.getId())) {
            return new Response<>(409, "ID de aeropuerto ya existe", null);
        }
        Aeropuerto copia = aeropuerto.clonar(); // Patrón Prototype
        servicio.crear(copia);
        return new Response<>(201, "Aeropuerto creado", copia);
    }

    public Response<ArrayList<Aeropuerto>> obtenerAeropuertosOrdenados() {
        ArrayList<Aeropuerto> aeropuertos = servicio.obtenerTodosOrdenadosPorId(); // Asegúrate de que estén ordenados por ID
        ArrayList<Aeropuerto> copia = aeropuertos.stream().map(Aeropuerto::clonar) // Patrón Prototype.collect(Collectors.toCollection(ArrayList::new));
        return new Response<>(200, "Aeropuertos obtenidos", copia);
    }

    private boolean validarAeropuerto(Aeropuerto a) {
        if (a == null) return false;
        if (!a.getId().matches("[A-Z]{3}")) return false;
        if (a.getNombre() == null || a.getNombre().trim().isEmpty()) return false;
        if (a.getLatitud() < -90 || a.getLatitud() > 90) return false;
        if (a.getLongitud() < -180 || a.getLongitud() > 180) return false;
        if (!tieneMax4Decimales(a.getLatitud())) return false;
        if (!tieneMax4Decimales(a.getLongitud())) return false;
        return true;
    }

    private boolean tieneMax4Decimales(double valor) {
        String[] partes = String.valueOf(valor).split("\\.");
        return partes.length < 2 || partes[1].length() <= 4;
    }
}

