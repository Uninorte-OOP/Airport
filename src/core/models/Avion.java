/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.services.ServicioVuelos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author NICOLAS ARIAS
 */
public class Avion implements Cloneable {
    private final String id;
    private String marca;
    private String modelo;
    private int capacidadMaxima;
    private String aerolinea;
    private final ArrayList<Vuelo> vuelos;

    public Avion(String id, String marca, String modelo, int capacidadMaxima, String aerolinea) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidadMaxima = capacidadMaxima;
        this.aerolinea = aerolinea;
        this.vuelos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public List<Vuelo> getVuelos() {
        return Collections.unmodifiableList(vuelos);
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    @Override
    public Avion clone() {
        try {
            Avion clon = (Avion) super.clone();
            clon.vuelos.clear();
            for (Vuelo v : this.vuelos) {
                clon.vuelos.add(v.clone());
            }
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Error al clonar avión", e);
        }
    }

    public static Avion desdeJSON(JSONObject json, ServicioVuelos servicioVuelos) {
        String id = json.getString("id");
        String marca = json.getString("marca");
        String modelo = json.getString("modelo");
        int capacidadMaxima = json.getInt("capacidadMaxima");
        String aerolinea = json.getString("aerolinea");

        Avion avion = new Avion(id, marca, modelo, capacidadMaxima, aerolinea);

        if (json.has("vuelos")) {
            JSONArray vuelosJSON = json.getJSONArray("vuelos");
            for (int i = 0; i < vuelosJSON.length(); i++) {
                String idVuelo = vuelosJSON.getString(i);
                Vuelo vuelo = servicioVuelos.buscarVuelo(idVuelo);
                if (vuelo != null) {
                    avion.agregarVuelo(vuelo);
                }
            }
        }
        return avion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avion)) return false;
        Avion avion = (Avion) o;
        return id.equals(avion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}