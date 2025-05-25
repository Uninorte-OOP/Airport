/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.Objects;
import org.json.JSONObject;

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
        if (idAeropuerto == null || idAeropuerto.isEmpty()) {
            throw new IllegalArgumentException("ID de aeropuerto no puede estar vacío");
        }
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

    public Ubicacion clonar() {
        return new Ubicacion(idAeropuerto, nombreAeropuerto, ciudad, pais, latitud, longitud);
    }

    public static Ubicacion desdeJSON(JSONObject json) {
        String idAeropuerto = json.getString("idAeropuerto");
        String nombreAeropuerto = json.getString("nombreAeropuerto");
        String ciudad = json.getString("ciudad");
        String pais = json.getString("pais");
        double latitud = json.getDouble("latitud");
        double longitud = json.getDouble("longitud");

        return new Ubicacion(idAeropuerto, nombreAeropuerto, ciudad, pais, latitud, longitud);
    }
    @Override
    public Ubicacion clone() {
        try {
            return (Ubicacion) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // No debería ocurrir
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ubicacion)) return false;
        Ubicacion that = (Ubicacion) o;
        return idAeropuerto.equals(that.idAeropuerto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAeropuerto);
    }
}
