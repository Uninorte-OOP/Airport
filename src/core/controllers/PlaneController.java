
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

public class PlaneController {
    public static Response createPlane(String id, String brand, String model, int maxCapacity, String airline){
        try {
            
            String maxCapacityS = String.valueOf(maxCapacity);
            try {
                if(!isValidPlaneIdFormat(id)){
                    return new Response("Format is invalid", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                if(id.equals("")){
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
            }
            
            if(brand.equals("")){
                return new Response("Brand must be not empty", Status.BAD_REQUEST);
            }
            
            if(model.equals("")){
                return new Response("Model must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                
                if(maxCapacity < 0){
                    return new Response("Max capacity must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if(maxCapacityS.equals("")){
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
    
    private static boolean isValidPlaneIdFormat(String id) {
        return id.matches("^[A-Z]{2}\\d{5}$");
}
}
