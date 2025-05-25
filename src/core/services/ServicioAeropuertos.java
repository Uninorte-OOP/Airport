/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Ubicacion;
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
public class ServicioAeropuertos {
    private final BaseDatosSimulada baseDatos;

    public ServicioAeropuertos(BaseDatosSimulada baseDatos) {
        this.baseDatos = baseDatos;
    }

    public void registrarAeropuerto(Ubicacion ubicacion) {
        if (ubicacion == null || baseDatos.getMapaUbicaciones().containsKey(ubicacion.getIdAeropuerto())) {
            throw new IllegalArgumentException("Ubicación inválida o ya registrada");
        }
        baseDatos.getMapaUbicaciones().put(ubicacion.getIdAeropuerto(), ubicacion);
    }

    public Ubicacion obtenerAeropuerto(String idAeropuerto) {
        return baseDatos.obtenerUbicacion(idAeropuerto);
    }

    public boolean existeAeropuerto(String idAeropuerto) {
        return baseDatos.getMapaUbicaciones().containsKey(idAeropuerto);
    }

    public Collection<Ubicacion> obtenerTodosLosAeropuertos() {
        return baseDatos.getMapaUbicaciones().values();
    }

    public ArrayList<Ubicacion> obtenerAeropuertosOrdenadosPorCiudad() {
        ArrayList<Ubicacion> lista = new ArrayList<>(obtenerTodosLosAeropuertos());
        lista.sort(Comparator.comparing(Ubicacion::getCiudad));
        return lista;
    }

    public ArrayList<Ubicacion> obtenerAeropuertosOrdenadosPorPais() {
        ArrayList<Ubicacion> lista = new ArrayList<>(obtenerTodosLosAeropuertos());
        lista.sort(Comparator.comparing(Ubicacion::getPais));
        return lista;
    }
}
