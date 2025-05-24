/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.Objects;

/**
 *
 * @author NICOLAS ARIAS
 */
public class Ubicacion implements Cloneable{
    private final String idAeropuerto;
    private String nombreAeropuerto;
    private String ciudad;
    private String pais;
    private double latitud;
    private double longitud;

    public Ubicacion(String idAeropuerto, String nombreAeropuerto, String ciudad, String pais, double latitud, double longitud) {
        this.idAeropuerto = idAeropuerto;
        this.nombreAeropuerto = nombreAeropuerto;
        this.ciudad = ciudad;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getIdAeropuerto() {
        return idAeropuerto;
    }

    public String getNombreAeropuerto() {
        return nombreAeropuerto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
