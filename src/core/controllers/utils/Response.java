/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

/**
 *
 * @author NICOLAS ARIAS
 */
public class Response<T> {
    
    private int codigo;
    private String mensaje;
    private T datos;

    public Response(int codigo, String mensaje, T datos) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getDatos() {
        return datos;
    }

    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Mensaje: " + mensaje + ", Datos: " + (datos != null ? datos.toString() : "null");
    }
}

