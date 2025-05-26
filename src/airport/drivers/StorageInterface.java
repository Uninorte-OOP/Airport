/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.drivers;

import airport.Flight;
import airport.Location;
import airport.Passenger;
import airport.Plane;
import airport.enums.AirportUser;
import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public interface StorageInterface {
    void savePassenger(Passenger passenger);
    void savePlane(Plane plane);
    void saveLocation(Location location);
    void saveFlight(Flight flight);
    ArrayList<Passenger> getPassengers();
    ArrayList<Plane> getPlanes();
    ArrayList<Location> getLocations();
    ArrayList<Flight> getFlights();
    void setCallback(Callback callback);

    public void setUserType(AirportUser airportUser);

    public AirportUser getUserType();

    public Plane getPlaneById(String planeId);

    public Location getLocationById(String departureLocationId);

    public int getSelectedPassengerId() ;

    public void setSelectedPassengerId(int passengerId);

    public boolean isValidPasengerId(String passengerId);
    
    public interface Callback {
        void onSavedPassenger();
        void onSavedPlane();
        void onSavedLocation();
        void onSavedFlight();
        void onSetPassengerId();
    }
}
