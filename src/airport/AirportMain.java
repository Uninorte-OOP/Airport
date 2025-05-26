/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport;

import airport.controllers.AirportController;
import airport.drivers.StorageInterface;
import airport.factories.StorageFactory;
import airport.interfaces.AirportControllerInterface;
import airport.interfaces.AirportViewInterface;
import airport.interfaces.AirportViewObserver;
import airport.loader.JSONDataLoader;
import java.util.List;

/**
 *
 * @author miguel
 */
public class AirportMain {
    public static void main(String[] args) {
        AirportViewInterface view = new AirportFrame();
        StorageInterface storage = StorageFactory.createStorage();
        AirportControllerInterface controller = new AirportController(view, storage);
        /*
        try {
            List<Passenger> defaultPassengers = JSONDataLoader.loadPassengers("json/passengers.json");
            storage.setPassengers(defaultPassengers);
            
            List<Plane> defaultPlanes = JSONDataLoader.loadPlanes("json/planes.json");
            storage.setPlanes(defaultPlanes);
            
            List<Location> defaultLocations = JSONDataLoader.loadLocations("json/locations.json");
            storage.setLocations(defaultLocations);
            
            List<Flight> defaultFlights = JSONDataLoader.loadFlights("json/flights.json");
            storage.setFlights(defaultFlights);
        }
        catch(Exception e) {
            System.out.println("AA3");
            System.out.println(e.toString());
        }
        */
        view.subscribeObserver((AirportViewObserver) controller);
        controller.startView();
    }
}
