/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Avion;
import core.models.Vuelo;
import core.models.storage.BaseDatosSimulada;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NICOLAS ARIAS
 */
public class ServicioAviones {
    private final BaseDatosSimulada baseDatos;

    public ServicioAviones(BaseDatosSimulada baseDatos) {
        this.baseDatos = baseDatos;
    }

    public Avion obtenerAvion(long id) {
        return baseDatos.obtenerAvion(id);
    }
    public void registrarAvion(Avion avion) {
        baseDatos.agregarAvion(avion);
    }
    public boolean existeAvion(long id) {
        return baseDatos.obtenerAvion(id) != null;
    }

    public Collection<Avion> obtenerTodosLosAviones() {
        return baseDatos.getMapaAviones().values(); 
    }

    public ArrayList<Avion> obtenerAvionesOrdenadosPorCapacidad() {
        ArrayList<Avion> lista = new ArrayList<>(obtenerTodosLosAviones());
        lista.sort(Comparator.comparingInt(Avion::getCapacidadMaxima));
        return lista;
    }

    public ArrayList<Avion> obtenerAvionesOrdenadosPorId() {
        ArrayList<Avion> lista = new ArrayList<>(obtenerTodosLosAviones());
        lista.sort(Comparator.comparing(Avion::getId));
        return lista;
    }

    public ArrayList<Avion> obtenerAvionesOrdenadosPorCantidadVuelos() {ArrayList<Avion> lista = new ArrayList<>(obtenerTodosLosAviones());
        lista.sort(Comparator.comparingInt(a -> a.getVuelos().size()));
        return lista;
    }

    public List<Vuelo> obtenerVuelosDeAvion(Long idAvion) {
        Avion avion = obtenerAvion(idAvion);
        return (avion != null) ? avion.getVuelos() : new ArrayList<>();
    }
}
