/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package airport.interfaces;

import airport.Flight;
import airport.Location;
import airport.Passenger;
import airport.Plane;
import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public interface AirportViewInterface {
    public void setVisible(boolean b);
    
    public void subscribeObserver(AirportViewObserver observer);

    public void enablePassengerTabs();
    public void enableAdministratorTabs();

    public void clearPassengerForm();
    public void clearAirplaneForm();
    public void clearLocationForm();
    public void clearFlightForm();

    public void updatePassengerLists(ArrayList<Passenger> passengers);
    public void updatePlaneLists(ArrayList<Plane> Planes);
    public void updateLocationLists(ArrayList<Location> Locations);
    public void updateFlightLists(ArrayList<Flight> Fligts);

    public void setPassengerIdInAddToFlight(String passengerId);

    public void updateMyFlightsList(ArrayList<Flight> flights);
    void showError(String message);

}
