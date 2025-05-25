/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.AeropuertoController;
import core.controllers.AvionController;
import core.controllers.PasajeroController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author NICOLAS ARIAS
 */
public class Vuelo implements Cloneable {
    private final String id;
    private final ArrayList<Pasajero> pasajeros;
    private Avion avion;
    private Ubicacion ubicacionSalida;
    private Ubicacion ubicacionEscala;
    private Ubicacion ubicacionLlegada;
    private LocalDateTime fechaSalida;
    private int horasDuracionLlegada;
    private int minutosDuracionLlegada;
    private int horasDuracionEscala;
    private int minutosDuracionEscala;

    // Constructor sin escala
    public Vuelo(String id, Avion avion, Ubicacion ubicacionSalida, Ubicacion ubicacionLlegada, LocalDateTime fechaSalida,int horasDuracionLlegada, int minutosDuracionLlegada) {
        this(id, avion, ubicacionSalida, null, ubicacionLlegada, fechaSalida,horasDuracionLlegada, minutosDuracionLlegada, 0, 0);
    }

    // Constructor general
    public Vuelo(String id, Avion avion, Ubicacion ubicacionSalida, Ubicacion ubicacionEscala, Ubicacion ubicacionLlegada,LocalDateTime fechaSalida, int horasDuracionLlegada, int minutosDuracionLlegada,int horasDuracionEscala, int minutosDuracionEscala) {
        this.id = id;
        this.pasajeros = new ArrayList<>();
        this.avion = avion;
        this.ubicacionSalida = ubicacionSalida;
        this.ubicacionEscala = ubicacionEscala;
        this.ubicacionLlegada = ubicacionLlegada;
        this.fechaSalida = fechaSalida;
        this.horasDuracionLlegada = horasDuracionLlegada;
        this.minutosDuracionLlegada = minutosDuracionLlegada;
        this.horasDuracionEscala = horasDuracionEscala;
        this.minutosDuracionEscala = minutosDuracionEscala;

        if (avion != null) {
            avion.agregarVuelo(this);
        }
    }

    public void agregarPasajero(Pasajero pasajero) {
        if (!pasajeros.contains(pasajero)) {
            pasajeros.add(pasajero);
            pasajero.agregarVuelo(this);
        }
    }

    public String getId() {
        return id;
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public Avion getAvion() {
        return avion;
    }

    public Ubicacion getUbicacionSalida() {
        return ubicacionSalida;
    }

    public Ubicacion getUbicacionEscala() {
        return ubicacionEscala;
    }

    public Ubicacion getUbicacionLlegada() {
        return ubicacionLlegada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public int getHorasDuracionLlegada() {
        return horasDuracionLlegada;
    }

    public int getMinutosDuracionLlegada() {
        return minutosDuracionLlegada;
    }

    public int getHorasDuracionEscala() {
        return horasDuracionEscala;
    }

    public int getMinutosDuracionEscala() {
        return minutosDuracionEscala;
    }

    public LocalDateTime calcularFechaLlegada() {
        return fechaSalida.plusHours(horasDuracionEscala).plusMinutes(minutosDuracionEscala).plusHours(horasDuracionLlegada).plusMinutes(minutosDuracionLlegada);
    }

    public void retrasar(int horas, int minutos) {
        this.fechaSalida = this.fechaSalida.plusHours(horas).plusMinutes(minutos);
    }

    public int obtenerCantidadPasajeros() {
        return pasajeros.size();
    }

    public boolean validarVuelo(Vuelo vuelo) {
    if (vuelo == null) return false;
    if (vuelo.getAvion() == null || vuelo.getAvion().getId() == null || vuelo.getAvion().getId().isEmpty()) return false;
    if (vuelo.getUbicacionSalida() == null || vuelo.getUbicacionSalida().getIdAeropuerto() == null || vuelo.getUbicacionSalida().getIdAeropuerto().isEmpty()) return false;
    if (vuelo.getUbicacionLlegada() == null || vuelo.getUbicacionLlegada().getIdAeropuerto() == null || vuelo.getUbicacionLlegada().getIdAeropuerto().isEmpty()) return false;

    int horas = vuelo.getHorasDuracionLlegada();
    int minutos = vuelo.getMinutosDuracionLlegada();
    if (horas == 0 && minutos == 0) return false;


    return true;
}
    public static Vuelo desdeJSON(JSONObject json, Avion avion, Ubicacion salida,Ubicacion llegada, Ubicacion escala,ArrayList<Pasajero> todosLosPasajeros) {

        String id = json.getString("id");
        LocalDateTime fechaSalida = LocalDateTime.parse(json.getString("fechaSalida"));

        int hLlegada = json.getInt("horasDuracionLlegada");
        int mLlegada = json.getInt("minutosDuracionLlegada");
        int hEscala = json.optInt("horasDuracionEscala", 0);
        int mEscala = json.optInt("minutosDuracionEscala", 0);

        Vuelo vuelo = new Vuelo(id, avion, salida, escala, llegada,fechaSalida, hLlegada, mLlegada, hEscala, mEscala);

        
        if (json.has("pasajeros")) {JSONArray idsPasajeros = json.getJSONArray("pasajeros");
            for (int i = 0; i < idsPasajeros.length(); i++) {
                long idPasajero = idsPasajeros.getLong(i);
                for (Pasajero p : todosLosPasajeros) {
                    if (p.getId() == idPasajero) {
                        vuelo.agregarPasajero(p);
                        break;
                    }
                }
            }
        }

        return vuelo;
    }
    public Vuelo clone() {
        try {
            Vuelo clon = (Vuelo) super.clone();
            clon.pasajeros.clear();
            for (Pasajero p : this.pasajeros) {
                clon.pasajeros.add(p.clone());
            }
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Error al clonar vuelo", e);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vuelo)) return false;
        Vuelo vuelo = (Vuelo) o;
        return id.equals(vuelo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
