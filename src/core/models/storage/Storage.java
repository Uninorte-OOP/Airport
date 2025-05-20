/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class Storage {
    private static Storage instance;
    private ArrayList<Flight> flights;
    private ArrayList<Location> locations;
    private ArrayList<Plane> planes;
    private ArrayList<Passenger> passengers;
    
    private Storage() {
        this.flights = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.planes = new ArrayList<>();
    }
    
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    
    public Flight getFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId() == id) {
                return flight;
            }
        }
        return null;
    }
    
    public boolean addFlight(Flight flight) {
        for (Flight f : this.flights) {
            if (f.getId() == flight.getId()) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }
    
    public boolean delFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId() == id) {
                this.flights.remove(flight);
                return true;
            }
        }
        return false;
    }
    
    public Location getLocation(String id) {
        for (Location location : this.locations) {
            if (location.getAirportId() == id) {
                return location;
            }
        }
        return null;
    }
    
    public Plane getPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId() == id) {
                return plane;
            }
        }
        return null;
    }
    
    public Passenger getPassenger(String id)  throws NumberFormatException {
        long idLong = Long.parseLong(id);
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == idLong) {
                return passenger;
            }
        }
        return null;
    }
}
