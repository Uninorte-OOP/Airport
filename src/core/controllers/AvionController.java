/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;


import core.controllers.utils.Response;
import core.models.Avion;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author NICOLAS ARIAS
 */
public class AvionController {
    private final ServicioAviones servicio;

    public AvionController(ServicioAviones servicio) {
        this.servicio = servicio;
    }

    public Response<Avion> crearAvion(Avion avion) {
        if (!validarAvion(avion)) {
            return new Response<>(400, "Datos inválidos", null);
        }
        if (servicio.existe(avion.getId())) {
            return new Response<>(409, "ID de avión ya existe", null);
        }

        Avion copia = avion.clonar(); // Patrón Prototype
        servicio.crear(copia);
        return new Response<>(201, "Avión creado", copia);
    }

    public Response<ArrayList<Avion>> obtenerAvionesOrdenados() {
        ArrayList<Avion> aviones = servicio.obtenerTodosOrdenadosPorId(); // Asegúrate de que esté ordenado
        ArrayList<Avion> copia = aviones.stream().map(Avion::clonar).collect(Collectors.toCollection(ArrayList::new));
        return new Response<>(200, "Aviones obtenidos", copia);
    }

    private boolean validarAvion(Avion a) {
        if (a == null) return false;
        if (!a.getId().matches("[A-Z]{2}\\d{5}")) return false;
        if (a.getModelo() == null || a.getModelo().trim().isEmpty()) return false;
        if (a.getMarca() == null || a.getMarca().trim().isEmpty()) return false;
        if (a.getCapacidad() <= 0) return false; // Suponiendo que el avión debe tener al menos una plaza

        return true;
    }
}

