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
import airport.enums.AirportUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String,Passenger> passengersMap;
    private ArrayList<Plane> planes;
    private Map<String, Plane> planesMap;
    private ArrayList<Location> locations;
    private Map<String, Location> locationsMap;
    private ArrayList<Flight> flights;
    private Callback callback;
    private AirportUser userType;
    private int selectedPassengerId;
    
    
    StorageFactoryImpl() {
        this.passengers = new ArrayList<Passenger>();
        this.planes = new ArrayList<Plane>();
        this.locations = new ArrayList<Location>();
        this.flights = new ArrayList<Flight>();
        this.passengersMap = new HashMap();
        this.planesMap = new HashMap();
        this.locationsMap = new HashMap();
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void setUserType(AirportUser airportUser) {
        this.userType = airportUser;
    }

    @Override
    public AirportUser getUserType() {
        return this.userType;
    }
    
    
    @Override
    public void savePassenger(Passenger passenger) {
        this.passengers.add(passenger);
        this.passengersMap.put(String.valueOf(passenger.getId()), passenger);
        this.callback.onSavedPassenger();
    }

    @Override
    public void savePlane(Plane plane) {
        this.planes.add(plane);
        this.planesMap.put(plane.getId(), plane);
        this.callback.onSavedPlane();
    }

    @Override
    public void saveLocation(Location location) {
        this.locations.add(location);
        this.locationsMap.put(location.getAirportId(), location);
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

    @Override
    public void saveFlight(Flight flight) {
        this.flights.add(flight);
        this.callback.onSavedFlight();
    }

    @Override
    public ArrayList<Flight> getFlights() {
        return this.flights;
    }

    @Override
    public Plane getPlaneById(String planeId) {
        return this.planesMap.get(planeId);
    }

    @Override
    public Location getLocationById(String locationId) {
        return this.locationsMap.get(locationId);
    }

    @Override
    public int getSelectedPassengerId() {
        return this.selectedPassengerId;
    }

    @Override
    public void setSelectedPassengerId(int passengerId) {
        this.selectedPassengerId = passengerId;
        this.callback.onSetPassengerId();
    }

    @Override
    public boolean isValidPasengerId(String passengerId) {
        return passengersMap.containsKey(passengerId);
    }

    
}
