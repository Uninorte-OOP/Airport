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
import java.util.Objects;

/**
 *
 * @author NICOLAS ARIAS
 */
public class Vuelo implements Cloneable {
    private final String id;
    private ArrayList<Pasajero> pasajeros;
    private Avion avion;
    private Ubicacion ubicacionSalida;
    private Ubicacion ubicacionEscala; // Puede ser null si no hay escala
    private Ubicacion ubicacionLlegada;
    private LocalDateTime fechaSalida;
    private int horasDuracionLlegada;
    private int minutosDuracionLlegada;
    private int horasDuracionEscala;
    private int minutosDuracionEscala;

    // Constructor sin escala
    public Vuelo(String id, Avion avion, Ubicacion ubicacionSalida, Ubicacion ubicacionLlegada, LocalDateTime fechaSalida, int horasDuracionLlegada, int minutosDuracionLlegada) {
        this.id = id;
        this.pasajeros = new ArrayList<>();
        this.avion = avion;
        this.ubicacionSalida = ubicacionSalida;
        this.ubicacionLlegada = ubicacionLlegada;
        this.fechaSalida = fechaSalida;
        this.horasDuracionLlegada = horasDuracionLlegada;
        this.minutosDuracionLlegada = minutosDuracionLlegada;
        this.horasDuracionEscala = 0;
        this.minutosDuracionEscala = 0;
        this.ubicacionEscala = null;

        avion.agregarVuelo(this);
    }

    // Constructor con escala
    public Vuelo(String id, Avion avion, Ubicacion ubicacionSalida, Ubicacion ubicacionEscala, Ubicacion ubicacionLlegada, LocalDateTime fechaSalida, int horasDuracionLlegada, int minutosDuracionLlegada, int horasDuracionEscala, int minutosDuracionEscala) {
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

        avion.agregarVuelo(this);
    }

    public void agregarPasajero(Pasajero pasajero) {
        pasajeros.add(pasajero);
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
        return fechaSalida
                .plusHours(horasDuracionEscala)
                .plusHours(horasDuracionLlegada)
                .plusMinutes(minutosDuracionEscala)
                .plusMinutes(minutosDuracionLlegada);
    }

    public void retrasar(int horas, int minutos) {
        this.fechaSalida = this.fechaSalida.plusHours(horas).plusMinutes(minutos);
    }

    public int obtenerCantidadPasajeros() {
        return pasajeros.size();
    }
}
