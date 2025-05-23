/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.drivers;

import airport.Location;
import airport.Passenger;
import airport.Plane;
import airport.controllers.AirportController;
import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public interface StorageInterface {
    void savePassenger(Passenger passenger);
    void savePlane(Plane plane);
    void saveLocation(Location location);
    ArrayList<Passenger> getPassengers();
    ArrayList<Plane> getPlanes();
    void setCallback(Callback callback);
    
    public interface Callback {
        void onSavedPassenger();
        void onSavedPlane();
        void onSavedLocation();
    }
}
