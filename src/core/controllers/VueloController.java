/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.models.Vuelo;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author NICOLAS ARIAS
 */
public class VueloController {
    private final ServicioVuelos servicio;
    private final ServicioAviones servicioAviones;
    private final ServicioAeropuertos servicioAeropuertos;
    private final ServicioPasajeros servicioPasajeros;

    public VueloController(ServicioVuelos servicio,ServicioAviones servicioAviones,ServicioAeropuertos servicioAeropuertos,ServicioPasajeros servicioPasajeros) {
        this.servicio = servicio;
        this.servicioAviones = servicioAviones;
        this.servicioAeropuertos = servicioAeropuertos;
        this.servicioPasajeros = servicioPasajeros;
    }

    public Response<Vuelo> crearVuelo(Vuelo vuelo) {
        if (!validarVuelo(vuelo)) {
            return new Response<>(400, "Datos inválidos", null);
        }
        if (servicio.existe(vuelo.getId())) {
            return new Response<>(409, "ID de vuelo ya existe", null);
        }
        Vuelo copia = vuelo.clonar();
        servicio.crear(copia);
        return new Response<>(201, "Vuelo creado", copia);
    }

    public Response<String> añadirPasajero(String idVuelo, long idPasajero) {
        if (!servicio.existe(idVuelo)) {
            return new Response<>(404, "Vuelo no encontrado", null);
        }
        if (!servicioPasajeros.existe(idPasajero)) {
            return new Response<>(404, "Pasajero no encontrado", null);
        }
        servicio.añadirPasajero(idVuelo, idPasajero);
        return new Response<>(200, "Pasajero añadido al vuelo", null);
    }

    public Response<String> retrasarVuelo(String idVuelo, int horas, int minutos) {
        if (!servicio.existe(idVuelo)) {
            return new Response<>(404, "Vuelo no encontrado", null);
        }
        if (horas < 0 || minutos < 0 || (horas == 0 && minutos == 0)) {
            return new Response<>(400, "Tiempo de retraso inválido", null);
        }
        servicio.retrasar(idVuelo, horas, minutos);
        return new Response<>(200, "Vuelo retrasado", null);
    }

    public Response<ArrayList<Vuelo>> obtenerVuelosOrdenados() {
        ArrayList<Vuelo> vuelos = servicio.obtenerTodosOrdenadosPorFecha();
        ArrayList<Vuelo> copia = vuelos.stream().map(Vuelo::clonar).collect(Collectors.toCollection(ArrayList::new));
        return new Response<>(200, "Vuelos obtenidos", copia);
    }

    public Response<ArrayList<Vuelo>> obtenerVuelosPorPasajero(long idPasajero) {
        if (!servicioPasajeros.existe(idPasajero)) {
            return new Response<>(404, "Pasajero no encontrado", null);
        }
        ArrayList<Vuelo> vuelos = servicio.obtenerVuelosPorPasajeroOrdenados(idPasajero);
        ArrayList<Vuelo> copia = vuelos.stream().map(Vuelo::clonar).collect(Collectors.toCollection(ArrayList::new));
        return new Response<>(200, "Vuelos del pasajero obtenidos", copia);
    }

    private boolean validarVuelo(Vuelo v) {
        if (v == null) return false;
        if (!v.getId().matches("[A-Z]{3}\\d{3}")) return false;
        if (!servicioAviones.existe(v.getAvionId())) return false;
        if (!servicioAeropuertos.existe(v.getOrigenId())) return false;
        if (!servicioAeropuertos.existe(v.getDestinoId())) return false;
        if (v.getFechaSalida() == null) return false;
        if (v.getDuracion() == null || v.getDuracion().isZero()) return false;
        return true;
    }
}

