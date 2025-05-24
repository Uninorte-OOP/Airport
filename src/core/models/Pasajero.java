/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author NICOLAS ARIAS
 */
public class Pasajero implements Cloneable{
    private final long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private int codigoPaisTelefono;
    private long telefono;
    private String pais;
    private ArrayList<Vuelo> vuelos;

    public Pasajero(long id, String nombre, String apellido, LocalDate fechaNacimiento, int codigoPaisTelefono, long telefono, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.codigoPaisTelefono = codigoPaisTelefono;
        this.telefono = telefono;
        this.pais = pais;
        this.vuelos = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getCodigoPaisTelefono() {
        return codigoPaisTelefono;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getPais() {
        return pais;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }
}
