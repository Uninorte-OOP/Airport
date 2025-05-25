/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author NICOLAS ARIAS
 */
public class Aeropuerto implements Cloneable{
    private final Ubicacion ubicacion;
    private final ArrayList<Vuelo> vuelosSalientes;
    private final ArrayList<Vuelo> vuelosEntrantes;

    public Aeropuerto(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
        this.vuelosSalientes = new ArrayList<>();
        this.vuelosEntrantes = new ArrayList<>();
    }

    public static Aeropuerto desdeJSON(JSONObject json) {
        JSONObject ubicacionJSON = json.getJSONObject("ubicacion");
        Ubicacion ubicacion = Ubicacion.desdeJSON(ubicacionJSON);
        return new Aeropuerto(ubicacion);
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public List<Vuelo> getVuelosSalientes() {
        return Collections.unmodifiableList(vuelosSalientes);
    }

    public List<Vuelo> getVuelosEntrantes() {
        return Collections.unmodifiableList(vuelosEntrantes);
    }

    public void agregarVueloSaliente(Vuelo vuelo) {
        vuelosSalientes.add(vuelo);
    }

    public void agregarVueloEntrante(Vuelo vuelo) {
        vuelosEntrantes.add(vuelo);
    }

    public String getId() {
        return ubicacion.getIdAeropuerto();
    }

    @Override
    public Aeropuerto clone() {
        try {
            Aeropuerto clon = (Aeropuerto) super.clone();
            Ubicacion ubicacionClonada = ubicacion.clone();
            clon = new Aeropuerto(ubicacionClonada);
            clon.vuelosSalientes.addAll(this.vuelosSalientes);
            clon.vuelosEntrantes.addAll(this.vuelosEntrantes);
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Error al clonar aeropuerto", e);
        }
    }
}
