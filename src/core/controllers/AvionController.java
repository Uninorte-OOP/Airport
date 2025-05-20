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
        Avion copia = avion.clonar();
        servicio.crear(copia);
        return new Response<>(201, "Avión creado", copia);
    }

    public Response<ArrayList<Avion>> obtenerAvionesOrdenados() {
        ArrayList<Avion> aviones = servicio.obtenerTodosOrdenadosPorId();
        ArrayList<Avion> copia = aviones.stream()
                                        .map(Avion::clonar)
                                        .collect(Collectors.toCollection(ArrayList::new));
        return new Response<>(200, "Aviones obtenidos", copia);
    }

    private boolean validarAvion(Avion a) {
        return a != null && a.getId().matches("[A-Z]{2}\\d{5}") && !a.getModelo().isEmpty();
    }
}

