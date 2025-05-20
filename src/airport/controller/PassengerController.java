/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import java.time.LocalDate;

/**
 *
 * @author Jose
 */
public class PassengerController {
    public static Responses createPassenger(long id1, String id, String firstname, LocalDate birthDate, int phoneCode, long phone, String lastname) {
        try {
            int idInt, ageInt;
            boolean genderB;
            
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Responses("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Responses("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if (firstname.equals("")) {
                return new Responses("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if (lastname.equals("")) {
                return new Responses("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
//            try {
//                ageInt = Integer.parseInt(LocalDate.of(year);
//                if (ageInt < 0) {
//                    return new Responses("Age must be positive", Status.BAD_REQUEST);
//                }
//            } catch (NumberFormatException ex) {
//                return new Responses("Age must be numeric", Status.BAD_REQUEST);
//            }
            
//            if (Country.equals("M")) {
//                genderB = false;
//            } else if (gender.equals("F")) {
//                genderB = true;
//            } else {
//                return new Responses("Gender error", Status.BAD_REQUEST);
//            }
            
//            Storage storage = Storage.getInstance();            
//            if (!storage.addPerson(new Person(idInt, firstname, lastname, ageInt, genderB))) {
//                return new Responses("A person with that id already exists", Status.BAD_REQUEST);
//            }
            return new Responses("Person created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
