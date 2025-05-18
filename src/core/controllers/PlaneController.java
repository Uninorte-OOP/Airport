
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

public class PlaneController {
    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline){
        try {
            if(id.equals("")){
                return new Response("Id must be not empty", Status.BAD_REQUEST);
            }
            if(){
            }
            
            if(brand.equals("")){
                return new Response("Brand must be not empty", Status.BAD_REQUEST);
            }
            
            if(model.equals("")){
                return new Response("Model must be not empty", Status.BAD_REQUEST);
            }
            
            int maxCapacityInt;
            try {
                maxCapacityInt = Integer.parseInt(maxCapacity);
                if(maxCapacityInt < 0){
                    return new Response("Max capacity must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if(maxCapacity.equals("")){
                return new Response("Max Capacity must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Max capacity must be just numeric", Status.BAD_REQUEST);
            }
            
            if(airline.equals("")){
                return new Response("Airline must be not empty", Status.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.BAD_REQUEST);
        }
    }
}
