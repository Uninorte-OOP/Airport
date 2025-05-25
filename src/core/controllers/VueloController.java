/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.models.Pasajero;
import core.models.Vuelo;
import core.services.ServicioAeropuertos;
import core.services.ServicioAviones;
import core.services.ServicioPasajeros;
import core.services.ServicioVuelos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author NICOLAS ARIAS
 */
public class VueloController {
    private final ServicioVuelos servicioVuelos;
    private final ServicioAviones servicioAviones;
    private final ServicioAeropuertos servicioAeropuertos;
    private final ServicioPasajeros servicioPasajeros;

    public VueloController(ServicioVuelos servicio,ServicioAviones servicioAviones,ServicioAeropuertos servicioAeropuertos,ServicioPasajeros servicioPasajeros) {
        this.servicioVuelos = servicio;
        this.servicioAviones = servicioAviones;
        this.servicioAeropuertos = servicioAeropuertos;
        this.servicioPasajeros = servicioPasajeros;
    }

    public Response<Vuelo> crearVuelo(Vuelo vuelo) {
        if (!validarVuelo(vuelo)) {
            return new Response<>(400, "Datos inválidos", null);
        }
        if (servicioVuelos.existeVuelo(vuelo.getId())) {
            return new Response<>(409, "ID de vuelo ya existe", null);
        }
        Vuelo copia = vuelo.clone();
        servicioVuelos.registrarVuelo(copia);
        return new Response<>(201, "Vuelo creado", copia);
    }

    public Response<String> añadirPasajero(String idVuelo, long idPasajero) {
        if (!servicioVuelos.existeVuelo(idVuelo)) {
            return new Response<>(404, "Vuelo no encontrado", null);
        }
        if (!servicioPasajeros.existePasajero(idPasajero)) {
            return new Response<>(404, "Pasajero no encontrado", null);
        }
        Pasajero pasajero = servicioPasajeros.buscarPasajero(idPasajero);
        servicioVuelos.añadirPasajero(idVuelo, pasajero);
        return new Response<>(200, "Pasajero añadido al vuelo", null);
    }

    public Response<String> retrasarVuelo(String idVuelo, int horas, int minutos) {
        if (!servicioVuelos.existeVuelo(idVuelo)) {
            return new Response<>(404, "Vuelo no encontrado", null);
        }
        if (horas < 0 || minutos < 0 || (horas == 0 && minutos == 0)) {
            return new Response<>(400, "Tiempo de retraso inválido", null);
        }
        servicioVuelos.retrasarVuelo(idVuelo, horas, minutos);
        return new Response<>(200, "Vuelo retrasado", null);
    }

    public Response<ArrayList<Vuelo>> obtenerVuelosOrdenados() {
        ArrayList<Vuelo> vuelos = servicioVuelos.obtenerVuelosOrdenadosPorFecha();
        ArrayList<Vuelo> copia = vuelos.stream().map(Vuelo::clone).collect(Collectors.toCollection(ArrayList::new));
        return new Response<>(200, "Vuelos obtenidos", copia);
    }

    public Response<ArrayList<Vuelo>> obtenerVuelosPorPasajero(long idPasajero) {
        if (!servicioPasajeros.existePasajero(idPasajero)) {
            return new Response<>(404, "Pasajero no encontrado", null);
        }
        ArrayList<Vuelo> vuelos = servicioVuelos.obtenerVuelosPorPasajeroOrdenados(idPasajero);
        ArrayList<Vuelo> copia = vuelos.stream().map(Vuelo::clone).collect(Collectors.toCollection(ArrayList::new));
        return new Response<>(200, "Vuelos del pasajero obtenidos", copia);
    }

    private boolean validarVuelo(Vuelo v) {
        if (v == null) return false;
    if (!v.getId().matches("[A-Z]{3}\\d{3}")) return false;

    if (v.getAvion() == null || !servicioAviones.existeAvion(v.getAvion().getId())) return false;
    if (v.getUbicacionSalida() == null || !servicioAeropuertos.existeAeropuerto(v.getUbicacionSalida().getIdAeropuerto())) return false;
    if (v.getUbicacionLlegada() == null || !servicioAeropuertos.existeAeropuerto(v.getUbicacionLlegada().getIdAeropuerto())) return false;

    if (v.getUbicacionEscala() == null) {
        if (v.getHorasDuracionEscala() != 0 || v.getMinutosDuracionEscala() != 0) return false;
    } else {
        if (!servicioAeropuertos.existeAeropuerto(v.getUbicacionEscala().getIdAeropuerto())) return false;
    }

    if (v.getFechaSalida() == null || v.getFechaSalida().isBefore(LocalDate.now().atStartOfDay())) return false;

    int horas = v.getHorasDuracionLlegada();
    int minutos = v.getMinutosDuracionLlegada();
    if (horas < 0 || minutos < 0) return false;
    if (horas == 0 && minutos == 0) return false;

    return true;
    }
}

