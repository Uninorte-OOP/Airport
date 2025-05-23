/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.factories;

import airport.Flight;
import airport.Location;
import airport.Passenger;
import airport.Plane;
import airport.drivers.StorageInterface;
import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public class StorageFactory {
    public static StorageInterface createStorage() {
        return new StorageFactoryImpl();
    }
}

class StorageFactoryImpl implements StorageInterface {
    private ArrayList<Passenger> passengers;
    private ArrayList<Plane> planes;
    private ArrayList<Location> locations;
    private ArrayList<Flight> flights;
    private Callback callback;
    
    
    StorageFactoryImpl() {
        this.passengers = new ArrayList<Passenger>();
        this.planes = new ArrayList<Plane>();
        this.locations = new ArrayList<Location>();
        this.flights = new ArrayList<Flight>();
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
    
    
    
    @Override
    public void savePassenger(Passenger passenger) {
        this.passengers.add(passenger);
        this.callback.onSavedPassenger();
    }

    @Override
    public void savePlane(Plane plane) {
        this.planes.add(plane);
        this.callback.onSavedPlane();
    }

    @Override
    public void saveLocation(Location location) {
        this.locations.add(location);
        this.callback.onSavedLocation();
    }

    @Override
    public ArrayList<Location> getLocations() {
        return this.locations;
    }
    

    @Override
    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

    @Override
    public ArrayList<Plane> getPlanes() {
        return this.planes;
    }
    
    
}
