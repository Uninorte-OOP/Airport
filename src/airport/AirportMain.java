/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport;

import airport.controllers.AirportController;
import airport.drivers.StorageInterface;
import airport.factories.StorageFactory;
import airport.interfaces.AirportControllerInterface;
import airport.interfaces.AirportModelInterface;
import airport.interfaces.AirportViewInterface;
import airport.interfaces.AirportViewObserver;
import airport.models.AirportModel;

/**
 *
 * @author miguel
 */
public class AirportMain {
    public static void main(String[] args) {
        AirportViewInterface view = new AirportFrame();
        AirportModelInterface model = new AirportModel();
        StorageInterface storage = StorageFactory.createStorage();
        AirportControllerInterface controller = new AirportController(model, view, storage);
        view.subscribeObserver((AirportViewObserver) controller);
        controller.startView();
    }
}
