/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package airport.interfaces;

import airport.Passenger;
import airport.Plane;
import java.util.ArrayList;

/**
 *
 * @author alejandro
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
}
