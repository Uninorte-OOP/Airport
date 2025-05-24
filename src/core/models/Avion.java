/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.ArrayList;
import java.util.Objects;

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
    private ArrayList<Vuelo> vuelos;

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

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public Avion clonar() {
        Avion copia = new Avion(id, marca, modelo, capacidadMaxima, aerolinea);
        
        return copia;
    }
}