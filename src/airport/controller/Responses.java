/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

/**
 *
 * @author Jose
 */
public class Responses {
    
    private String message;
    private int status;
    private Object object;

    public Responses(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public Responses(String message, int status, Object object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }
    

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }
    
}
