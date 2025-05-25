package airport.controllers;

import airport.Flight;
import airport.Location;
import airport.Passenger;
import airport.Plane;
import airport.drivers.StorageInterface;
import airport.enums.AirportUser;
import airport.interfaces.AirportControllerInterface;
import airport.interfaces.AirportViewInterface;
import airport.interfaces.AirportViewObserver;
import airport.pojo.AirplaneForm;
import airport.pojo.FlightForm;
import airport.pojo.LocationForm;
import airport.pojo.PassengerForm;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miguel
 */
public class AirportController implements AirportControllerInterface, AirportViewObserver, StorageInterface.Callback {
    private AirportViewInterface view;
    private StorageInterface storage;
    
    public AirportController(AirportViewInterface view, StorageInterface storage) {
        this.view = view;
        this.storage = storage;
        this.storage.setCallback(this);
    }

    @Override
    public void startView() {
        this.view.setVisible(true);
    }
    
    @Override
    public void onSelectedPassengerType() {
        this.storage.setUserType(AirportUser.Passenger);
        updateMainTab();
    }
    
    @Override
    public void onSelectedAdministratorType() {
        this.storage.setUserType(AirportUser.Administrator);
        updateMainTab();
    }
    
    private void updateMainTab() {
        if(this.storage.getUserType() == AirportUser.Passenger) {
            this.view.enablePassengerTabs();
        }
        if(this.storage.getUserType() == AirportUser.Administrator) {
            this.view.enableAdministratorTabs();
        }
    }
    
    @Override
    public void onRegisterPassengerIntent(PassengerForm form) {
        boolean isValidForm = isPassengerFormValid(form);
        if(!isValidForm) {
            return;
        }
        
        int year = Integer.parseInt(form.getYear());
        int month = Integer.parseInt(form.getMonth());
        int day = Integer.parseInt(form.getDay());
        int phoneCode = Integer.parseInt(form.getPhoneCode());
        long phone = Long.parseLong(form.getPhone());

        LocalDate birthDate = LocalDate.of(year, month, day);
        Passenger passenger = new Passenger(Long.parseLong(form.getId()), form.getFirstname(), form.getLastname(), birthDate, phoneCode, phone, form.getCountry());
        this.storage.savePassenger(passenger);
    }
    
    @Override
    public void onSavedPassenger() {
        this.view.clearPassengerForm();
        this.view.updatePassengerLists(this.storage.getPassengers());
    }
    
    private boolean isPassengerFormValid(PassengerForm form) {
        return true;
    }
    
    @Override
    public void onRegisterAirplaneIntent(AirplaneForm form) {
        boolean isValidForm = isAirplaneFormValid(form);
        if(!isValidForm) {
            return;
        }
        Plane plane = new Plane(form.getId(), form.getBrand(), form.getModel(), Integer.parseInt(form.getMaxCapacity()), form.getAirline());
        this.storage.savePlane(plane); 
    }

    @Override
    public void onSavedPlane(){
        this.view.clearAirplaneForm();
        this.view.updatePlaneLists(this.storage.getPlanes());
    }
    
    private boolean isAirplaneFormValid(AirplaneForm form) {
        return true;
    }
    
    @Override
    public void onRegisterLocationIntent(LocationForm form){
        boolean isValidForm = isLocationFormValid(form);
        if(!isValidForm) {
            return;
        }
        
        Location location = new Location(form.getAirportId(),form.getAirportName(), form.getAirportCity(), form.getAirportCountry(), Double.parseDouble(form.getAirportLatitude()), Double.parseDouble(form.getAirportLongitude()));
        this.storage.saveLocation(location);
    }
    
    @Override
    public void onSavedLocation(){
        this.view.clearLocationForm();
        this.view.updateLocationLists(this.storage.getLocations());
    }

    private boolean isLocationFormValid(LocationForm form) {
        return true;
    }
    
    @Override
    public void onRegisterFlightIntent (FlightForm form){
        boolean isValidForm = isFlightFormValid(form);
        if(!isValidForm) {    
            return;
        }
        
        Flight flight = new Flight(
            form.getId(),
            storage.getPlaneById(form.getPlaneId()),
            storage.getLocationById(form.getDepartureLocationId()),
            storage.getLocationById(form.getScaleLocationId()),
            storage.getLocationById(form.getArrivalLocationId()),
            LocalDateTime.of(form.getYear(), form.getMonth(), form.getDay(), form.getHoursDeparture(), form.getMinutesDeparture()),
            form.getHoursDurationsArrival(),
            form.getMinutesDurationsArrival(),
            form.getHoursDurationsScale(),
            form.getMinutesDurationsScale()
        );
        
        this.storage.saveFlight(flight);    
    }

    private boolean isFlightFormValid(FlightForm form) {
        return true;
    }

    @Override
    public void onSavedFlight() {
        this.view.clearFlightForm();
        this.view.updateFlightLists(this.storage.getFlights());
    }
    
}
