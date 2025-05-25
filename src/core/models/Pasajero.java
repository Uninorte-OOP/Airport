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
    private String firstname;
    private String lastname;
    private LocalDate fechaNacimiento;
    private int phoneCode;
    private long phone;
    private String country;
    private ArrayList<Vuelo> vuelos;

    public Pasajero(long id, String firstname, String lastname, LocalDate fechaNacimiento, int phoneCode, long phone, String country) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fechaNacimiento = fechaNacimiento;
        this.phoneCode = phoneCode;
        this.phone = phone;
        this.country = country;
        this.vuelos = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return firstname;
    }

    public String getApellido() {
        return lastname;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getCodigoPaisTelefono() {
        return phoneCode;
    }

    public long getTelefono() {
        return phone;
    }

    public String getPais() {
        return country;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }
}
