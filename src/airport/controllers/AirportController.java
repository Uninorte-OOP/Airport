package airport.controllers;

import airport.Location;
import airport.Passenger;
import airport.Plane;
import airport.drivers.StorageInterface;
import airport.interfaces.AirportControllerInterface;
import airport.interfaces.AirportModelInterface;
import airport.interfaces.AirportViewInterface;
import airport.interfaces.AirportViewObserver;
import airport.pojo.AirplaneForm;
import airport.pojo.FlightForm;
import airport.pojo.LocationForm;
import airport.pojo.PassengerForm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miguel
 */
public class AirportController implements AirportControllerInterface, AirportViewObserver, StorageInterface.Callback {
    private AirportModelInterface model;
    private AirportViewInterface view;
    private StorageInterface storage;
    
    public AirportController(AirportModelInterface model, AirportViewInterface view, StorageInterface storage) {
        this.model = model;
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
        this.view.enablePassengerTabs();
    }
    
    @Override
    public void onSelectedAdministratorType() {
        this.view.enableAdministratorTabs();
    }
    
    @Override
    public void onRegisterPassengerIntent(PassengerForm form) {
        boolean isValidForm = isPassengerFormValid(form);
        if(!isValidForm) {
            return;
        }
        
        Passenger passenger = new Passenger(form);
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
        Plane plane = new Plane(form);
        this.storage.savePlane(plane); 
    }
    
    @Override
    public void onSavedPlane(){
        this.view.clearAirplaneForm();    
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
        
        Location location = new Location(form);
        this.storage.saveLocation(location);
        
    }
    
    @Override
    public void onSavedLocation(){
        this.view.clearLocationForm();
    }

    private boolean isLocationFormValid(LocationForm form) {
        return true;
    }
    
    @Override
    public void onRegisterFlightIntent (FlightForm form){
        boolean isValidForm = isFlightFormValid(form);
        if(isValidForm) {
            this.view.clearFlightForm();    
        }
        else {
            //this.view.clearPassengerForm();
        } 
    }

    private boolean isFlightFormValid(FlightForm form) {
        return true;
    }
    
}
