/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.pojo;



/**
 *
 * @author miguel
 */
public class AirplaneForm {

    public String airline;
    public String maxCapacity;
    public String model;
    public String brand;
    public String id;
    public AirplaneForm(String id, String brand, String model, String maxCapacity, String airline) {
       this.id = id;
       this.airline = airline;
       this.brand = brand;
       this.model = model;
       this.maxCapacity = maxCapacity;
    }

    public String getAirline() {
        return airline;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getId() {
        return id;
    }   
}
