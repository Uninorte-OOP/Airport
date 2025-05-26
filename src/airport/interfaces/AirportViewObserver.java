/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package airport.interfaces;

import airport.pojo.AirplaneForm;
import airport.pojo.FlightForm;
import airport.pojo.LocationForm;
import airport.pojo.PassengerForm;

/**
 *
 * @author miguel
 */
public interface AirportViewObserver {
    void onSelectedPassengerType();
    void onSelectedAdministratorType();
    void onRegisterPassengerIntent(PassengerForm form);
    void onRegisterAirplaneIntent(AirplaneForm form);;

    public void onRegisterLocationIntent(LocationForm form);

    public void onRegisterFlightIntent(FlightForm form);

    public void onOpenAddToFlightView();

    public void onSelectedPassengerId(String selectedId);
}
