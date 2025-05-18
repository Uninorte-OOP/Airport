
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

public class LocationController {
    private static boolean isValidAirportId(String id) {
        if (id.equals("") && id.length() != 3){
                return false;
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isUpperCase(id.charAt(i))) {
               return false;
            }
        }
               return true;
    }
    
    public static Response createLocation(String id, String name, String city, String country, String latitude, String longitude){
        try {
            double latitudeD;
            double longitudeD;
            
            try {
                if(!isValidAirportId(id)) {
                    return new Response("ID must be 3 uppercase letters ", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                if(id.equals("")){
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
            }
            
            if(name.equals("")){
                return new Response("Name must be not empty", Status.BAD_REQUEST);
            }
            
            if(city.equals("")){
                return new Response("City must be not empty", Status.BAD_REQUEST);
            }
            if(country.equals("")){
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            try {
                latitudeD = Double.parseDouble(latitude);
                if (!latitude.matches("-?\\d+(\\.\\d{1,4})?")) {
                    return new Response("Latitude must have max 4 decimals", Status.BAD_REQUEST);
                }
                if(latitudeD < -90 && latitudeD > 90){
                    return new Response("Latitude is in incorrect range", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if(latitude.equals("")){
                    return new Response("Latitude must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Latitude must be numeric", Status.BAD_REQUEST);
            }
            
            try {
                longitudeD = Double.parseDouble(longitude);
                if (!longitude.matches("-?\\d+(\\.\\d{1,4})?")) {
                    return new Response("Longitude must have max 4 decimals", Status.BAD_REQUEST);
                }
                if(longitudeD < -180 && longitudeD > 180){
                    return new Response("Longitude is in incorrect range", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if(longitude.equals("")){
                    return new Response("Longitude must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Longitude must be numeric", Status.BAD_REQUEST);
            }
            
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
