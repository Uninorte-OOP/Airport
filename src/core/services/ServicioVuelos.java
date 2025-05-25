/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Pasajero;
import core.models.Vuelo;
import core.models.storage.BaseDatosSimulada;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NICOLAS ARIAS
 */
public class ServicioVuelos {
    private final BaseDatosSimulada baseDatos;

    public ServicioVuelos(BaseDatosSimulada baseDatos) {
        this.baseDatos = baseDatos;
    }

    public Vuelo buscarVuelo(String id) {
        return baseDatos.obtenerVuelo(id);
    }

    public boolean existeVuelo(String idVuelo) {
        return baseDatos.obtenerVuelo(idVuelo) != null;
    }

    public void eliminarVuelo(String idVuelo) {
        baseDatos.getMapaVuelos().remove(idVuelo);
    }

    public Collection<Vuelo> obtenerTodosLosVuelos() {
        return baseDatos.getMapaVuelos().values();
    }

    public ArrayList<Vuelo> obtenerVuelosOrdenadosPorFecha() {
        ArrayList<Vuelo> lista = new ArrayList<>(obtenerTodosLosVuelos());
        lista.sort(Comparator.comparing(Vuelo::getFechaSalida));
        return lista;
    }

    public ArrayList<Vuelo> obtenerVuelosOrdenadosPorId() {
        ArrayList<Vuelo> lista = new ArrayList<>(obtenerTodosLosVuelos());
        lista.sort(Comparator.comparing(Vuelo::getId));
        return lista;
    }

    public int cantidadVuelos() {
        return baseDatos.getMapaVuelos().size();
    }
    public void registrarVuelo(Vuelo vuelo) {
        baseDatos.agregarVuelo(vuelo);
    }
    public void retrasarVuelo(String idVuelo, int horas, int minutos) {
        Vuelo vuelo = buscarVuelo(idVuelo);
        if (vuelo != null) {
            vuelo.retrasar(horas, minutos);
        } else {
            throw new IllegalArgumentException("Vuelo no encontrado: " + idVuelo);
        }
    }

    public void añadirPasajero(String idVuelo, Pasajero pasajero) {
        Vuelo vuelo = buscarVuelo(idVuelo);
        if (vuelo != null) {
            vuelo.agregarPasajero(pasajero);
            pasajero.agregarVuelo(vuelo);
        } else {
            throw new IllegalArgumentException("Vuelo no encontrado: " + idVuelo);
        }
    }

    public ArrayList<Vuelo> obtenerVuelosPorPasajeroOrdenados(long idPasajero) {
        ArrayList<Vuelo> resultado = new ArrayList<>();
        for (Vuelo vuelo : obtenerTodosLosVuelos()) {
            if (vuelo.getPasajeros().stream().anyMatch(p -> p.getId() == idPasajero)) {
                resultado.add(vuelo);
            }
        }
        resultado.sort(Comparator.comparing(Vuelo::getFechaSalida));
        return resultado;
    }
}

