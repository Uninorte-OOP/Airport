/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Passenger;
import airport.models.storages.PassengerStorage;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Santiago Solorzano
 */
public class PassengerController {
    private final PassengerStorage passengerStorage;

    public PassengerController() throws Exception {
        this.passengerStorage = PassengerStorage.getInstance();
    }

    public Response registerPassenger(
        String idText, 
        String firstName, 
        String lastName,
        String yearText,
        String monthText,
        String dayText,
        String phoneCodeText,
        String phoneText,
        String country
    ) {
        try {
            // 1. Validación de campos obligatorios
            if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                country == null || country.trim().isEmpty()) {
                return new Response("Nombre, apellido y país son obligatorios", Status.BAD_REQUEST);
            }
            
            // 2. Validación y conversión de ID
            long id;
            try {
                id = Long.parseLong(idText);
                if (id <= 0) {
                    return new Response("ID debe ser un número positivo", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("ID debe ser un número válido", Status.BAD_REQUEST);
            }
            // Validación de unicidad de ID
            if (passengerStorage.getPassengerById(id) != null) {
                return new Response("El ID de pasajero ya existe", Status.BAD_REQUEST);
            }
            // Validación de fecha de nacimiento
            LocalDate birthDate;
            try {
                int year = Integer.parseInt(yearText);
                int month = Integer.parseInt(monthText);
                int day = Integer.parseInt(dayText);
                
                birthDate = LocalDate.of(year, month, day);
                if (birthDate.isAfter(LocalDate.now())) {
                    return new Response("Fecha de nacimiento no puede ser futura", Status.BAD_REQUEST);
                }
                
                int age = Period.between(birthDate, LocalDate.now()).getYears();
                if (age < 18) {
                    return new Response("El pasajero debe ser mayor de edad", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Fecha de nacimiento inválida", Status.BAD_REQUEST);
            }

            // Validación de teléfono
            int phoneCode;
            long phone;
            try {
                phoneCode = Integer.parseInt(phoneCodeText);
                phone = Long.parseLong(phoneText);
                if (phone <= 0) {
                    return new Response("Número de teléfono inválido", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Código y número de teléfono deben ser numéricos", Status.BAD_REQUEST);
            }

            // Verificar si el pasajero ya existe
            if (passengerStorage.getPassengerById(id) != null) {
                return new Response("El pasajero con este ID ya existe", Status.BAD_REQUEST);
            }

            // Crear y guardar el pasajero
            Passenger newPassenger = new Passenger(id, firstName, lastName, birthDate, phoneCode, phone, country);
            boolean success = passengerStorage.addPassenger(newPassenger);
            
            if (success) {
                return new Response("Pasajero registrado exitosamente", Status.CREATED, newPassenger);
            } else {
                return new Response("Error al registrar pasajero", Status.INTERNAL_SERVER_ERROR);
            }
            
        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
