
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.StoragePassenger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PassengerController {
    public static Response registerPassenger(String id, String firstname, String lastname, 
            String birthdate, String countryPhoneCode, String phone, String country){
        try {
            long idLong;
            LocalDateTime birthdateTime;
            int countryPhoneCodeInt;
            long phoneLong;
            
            try {
                idLong = Long.parseLong(id);
                if(idLong < 0 ){
                    return new Response("Id must be not negative", Status.BAD_REQUEST);
                }
                if(id.length() > 15){
                    return new Response("Id must have less than 15 digits", Status.BAD_REQUEST);
                }
               
            } catch (NumberFormatException e) {
                if(id.equals("")){
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if(firstname.equals("")){
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);  
            }
            if(lastname.equals("")){
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
                LocalDate birthDate = LocalDate.parse(birthdate.trim(), formatter);
                LocalDate today = LocalDate.now();
                    if (birthDate.isAfter(today)) {
                        return new Response("Birthdate can not be in the future", Status.BAD_REQUEST);
                    }
                    if (birthDate.isBefore(today.minusYears(120))) {
                        return new Response("Birthdate is too old", Status.BAD_REQUEST);
                    }
            } catch (DateTimeParseException e) {
                    return new Response("Invalid date format", Status.BAD_REQUEST);
            }
            
            try {
                countryPhoneCodeInt = Integer.parseInt(countryPhoneCode);
                if(countryPhoneCode.length() > 3){
                    return new Response("Country phone code must have 3 or less than 3 digits", Status.BAD_REQUEST);
                }
                if(countryPhoneCodeInt < 0){
                    return new Response("Country phone code must be greater than 0", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException e) {
                if(countryPhoneCode.equals("")){
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Country phone code must be numeric", Status.BAD_REQUEST);
            }
            
            try {
                phoneLong = Long.parseLong(phone);
                if(phoneLong < 0){
                    return new Response("Phone must be greater than 0", Status.BAD_REQUEST);
                }
                if (phone.length() > 11){
                    return new Response("Phone must have less than 11 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                if(phone.equals("")){
                return new Response("Phone must be not empty", Status.BAD_REQUEST);
            }
                return new Response("Phone number must be numeric", Status.BAD_REQUEST);
            }
            
            if(country.equals("")){
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            
            StoragePassenger dataManager = StoragePassenger.getInstance();
            return new Response("Passenger registered succesfully", Status.CREATED);
            
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
}
