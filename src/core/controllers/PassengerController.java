
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.StoragePassenger;
import java.time.LocalDate;


public class PassengerController {
    public static Response registerPassenger(long id, String firstname, String lastname, 
            int year, int month, int day, int countryPhoneCode, long phone, String country){
        try {
            
            String idS = String.valueOf(id);
            String yearS= String.valueOf(year);
            String monthS= String.valueOf(month);
            String dayS= String.valueOf(day);
            String countryPhoneCodeS = String.valueOf(countryPhoneCode);
            String phoneS = String.valueOf(phone);
            try {
                if(id < 0 ){
                    return new Response("Id must be not negative", Status.BAD_REQUEST);
                }
                if(idS.length() > 15){
                    return new Response("Id must have less than 15 digits", Status.BAD_REQUEST);
                }
               
            } catch (NumberFormatException e) {
                if(idS.equals("")){
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
                
                if(year > 2025){
                    return new Response("Year invalid", Status.BAD_REQUEST);
                }
                if (yearS.length() > 4) {
                    return new Response("Date of year invalid", Status.BAD_REQUEST);
                }

                if (month < 1  & month > 12) {
                    return new Response("Month invalid", Status.BAD_REQUEST);
                }
                if (day < 1 & day > 31) {
                    return new Response("Day invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if(yearS.equals("") && monthS.equals("") && dayS.equals("")){
                    return new Response ("Birthdate must be not empty",Status.BAD_REQUEST);
                }
                return new Response("Birthdate must be just numeric", Status.BAD_REQUEST);
            }
            
            try {
                
                if(countryPhoneCodeS.length() > 3){
                    return new Response("Country phone code must have 3 or less than 3 digits", Status.BAD_REQUEST);
                }
                if(countryPhoneCode < 0){
                    return new Response("Country phone code must be greater than 0", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException e) {
                if(countryPhoneCodeS.equals("")){
                    return new Response("Country phone code must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Country phone code must be numeric", Status.BAD_REQUEST);
            }
            
            try {
                if(phone < 0){
                    return new Response("Phone must be greater than 0", Status.BAD_REQUEST);
                }
                if (phoneS.length() > 11){
                    return new Response("Phone must have less than 11 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                if(phoneS.equals("")){
                return new Response("Phone must be not empty", Status.BAD_REQUEST);
            }
                return new Response("Phone number must be numeric", Status.BAD_REQUEST);
            }
            
            if(country.equals("")){
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            
            LocalDate birthDate = LocalDate.of(year, month, day);
            StoragePassenger storagePass = StoragePassenger.getInstance();
            if(!storagePass.addPassenger(new Passenger(id, firstname, lastname, birthDate, 
                    countryPhoneCode, phone, country))){
                return new Response("Passenger with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Passenger registered successfully", Status.CREATED);
            
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
}
