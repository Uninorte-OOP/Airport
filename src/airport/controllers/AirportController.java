package airport.controllers;

import airport.Flight;
import airport.Location;
import airport.Passenger;
import airport.Plane;
import airport.drivers.StorageInterface;
import airport.enums.AirportUser;
import airport.interfaces.AirportControllerInterface;
import airport.interfaces.AirportViewInterface;
import airport.interfaces.AirportViewObserver;
import airport.pojo.AirplaneForm;
import airport.pojo.FlightForm;
import airport.pojo.LocationForm;
import airport.pojo.PassengerForm;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miguel
 */
public class AirportController implements AirportControllerInterface, AirportViewObserver, StorageInterface.Callback {
    private AirportViewInterface view;
    private StorageInterface storage;
    
    public AirportController(AirportViewInterface view, StorageInterface storage) {
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
        this.storage.setUserType(AirportUser.Passenger);
        updateMainTab();
    }
    
    @Override
    public void onSelectedAdministratorType() {
        this.storage.setUserType(AirportUser.Administrator);
        updateMainTab();
    }
    
    private void updateMainTab() {
        if(this.storage.getUserType() == AirportUser.Passenger) {
            this.view.enablePassengerTabs();
        }
        if(this.storage.getUserType() == AirportUser.Administrator) {
            this.view.enableAdministratorTabs();
        }
    }
    
    @Override
    public void onRegisterPassengerIntent(PassengerForm form) {
        boolean isValidForm = isPassengerFormValid(form);
        if(!isValidForm) {
            return;
        }
        
        int year = Integer.parseInt(form.getYear());
        int month = Integer.parseInt(form.getMonth());
        int day = Integer.parseInt(form.getDay());
        int phoneCode = Integer.parseInt(form.getPhoneCode());
        long phone = Long.parseLong(form.getPhone());

        LocalDate birthDate = LocalDate.of(year, month, day);
        Passenger passenger = new Passenger(Long.parseLong(form.getId()), form.getFirstname(), form.getLastname(), birthDate, phoneCode, phone, form.getCountry());
        this.storage.savePassenger(passenger);
    }
    
    @Override
    public void onSavedPassenger() {
        this.view.clearPassengerForm();
        this.view.updatePassengerLists(this.storage.getPassengers());
    }
    
    private boolean isPassengerFormValid(PassengerForm form) {
    // Validación de campos vacíos
    if (form.getId().isEmpty() || form.getFirstname().isEmpty() || form.getLastname().isEmpty() ||
        form.getYear().isEmpty() || form.getMonth().isEmpty() || form.getDay().isEmpty() ||
        form.getPhoneCode().isEmpty() || form.getPhone().isEmpty() || form.getCountry().isEmpty()) {
        view.showError("Todos los campos son obligatorios.");
        return false;
    }

    // Validación ID (único, numérico, >=0, máx. 15 dígitos)
    long id;
    try {
        id = Long.parseLong(form.getId());
        if (id < 0 || form.getId().length() > 15) {
            view.showError("El ID del pasajero debe ser un número positivo de máximo 15 dígitos.");
            return false;
        }
        if (storage.getPassengers().stream().anyMatch(p -> p.getId() == id)) {
            view.showError("El ID del pasajero ya está registrado.");
            return false;
        }
    } catch (NumberFormatException e) {
        view.showError("El ID del pasajero debe ser numérico.");
        return false;
    }

    // Validación fecha de nacimiento
    try {
        int year = Integer.parseInt(form.getYear());
        int month = Integer.parseInt(form.getMonth());
        int day = Integer.parseInt(form.getDay());
        LocalDate birthDate = LocalDate.of(year, month, day);
    } catch (Exception e) {
        view.showError("La fecha de nacimiento no es válida.");
        return false;
    }

    // Validación código telefónico (≥ 0, máx. 3 dígitos)
    try {
        int phoneCode = Integer.parseInt(form.getPhoneCode());
        if (phoneCode < 0 || form.getPhoneCode().length() > 3) {
            view.showError("El código telefónico debe ser un número positivo de máximo 3 dígitos.");
            return false;
        }
    } catch (NumberFormatException e) {
        view.showError("El código telefónico debe ser numérico.");
        return false;
    }

    // Validación teléfono (≥ 0, máx. 11 dígitos)
    try {
        long phone = Long.parseLong(form.getPhone());
        if (phone < 0 || form.getPhone().length() > 11) {
            view.showError("El número telefónico debe ser un número positivo de máximo 11 dígitos.");
            return false;
        }
    } catch (NumberFormatException e) {
        view.showError("El número telefónico debe ser numérico.");
        return false;
    }

    return true;
}

    
    @Override
    public void onRegisterAirplaneIntent(AirplaneForm form) {
        boolean isValidForm = isAirplaneFormValid(form);
        if(!isValidForm) {
            return;
        }
        Plane plane = new Plane(form.getId(), form.getBrand(), form.getModel(), Integer.parseInt(form.getMaxCapacity()), form.getAirline());
        this.storage.savePlane(plane); 
    }

    @Override
    public void onSavedPlane(){
        this.view.clearAirplaneForm();
        this.view.updatePlaneLists(this.storage.getPlanes());
    }
    
    private boolean isAirplaneFormValid(AirplaneForm form) {
    // Validación de campos vacíos
    if (form.getId().isEmpty() || form.getBrand().isEmpty() ||
        form.getModel().isEmpty() || form.getAirline().isEmpty() ||
        form.getMaxCapacity().isEmpty()) {
        view.showError("Todos los campos del avión son obligatorios.");
        return false;
    }

    // Validar formato ID: XXYYYYY
    if (!form.getId().matches("^[A-Z]{2}\\d{5}$")) {
        view.showError("El ID del avión debe tener el formato XXYYYYY (dos letras mayúsculas y cinco dígitos).");
        return false;
    }

    // Verificar unicidad del ID
    if (storage.getPlanes().stream().anyMatch(p -> p.getId().equals(form.getId()))) {
        view.showError("El ID del avión ya está registrado.");
        return false;
    }

    // Validar capacidad máxima
    try {
        int capacity = Integer.parseInt(form.getMaxCapacity());
        if (capacity <= 0) {
            view.showError("La capacidad máxima debe ser un número mayor que cero.");
            return false;
        }
    } catch (NumberFormatException e) {
        view.showError("La capacidad máxima debe ser un número válido.");
        return false;
    }

    return true;
}

    
    @Override
    public void onRegisterLocationIntent(LocationForm form){
        boolean isValidForm = isLocationFormValid(form);
        if(!isValidForm) {
            return;
        }
        
        Location location = new Location(form.getAirportId(),form.getAirportName(), form.getAirportCity(), form.getAirportCountry(), Double.parseDouble(form.getAirportLatitude()), Double.parseDouble(form.getAirportLongitude()));
        this.storage.saveLocation(location);
    }
    
    @Override
    public void onSavedLocation(){
        this.view.clearLocationForm();
        this.view.updateLocationLists(this.storage.getLocations());
    }

    private boolean isLocationFormValid(LocationForm form) {
    // Validar campos vacíos
    if (form.getAirportId().isEmpty() || form.getAirportName().isEmpty() ||
        form.getAirportCity().isEmpty() || form.getAirportCountry().isEmpty() ||
        form.getAirportLatitude().isEmpty() || form.getAirportLongitude().isEmpty()) {
        view.showError("Todos los campos del aeropuerto son obligatorios.");
        return false;
    }

    // Validar ID (3 letras mayúsculas)
    if (!form.getAirportId().matches("^[A-Z]{3}$")) {
        view.showError("El ID del aeropuerto debe contener exactamente 3 letras mayúsculas.");
        return false;
    }
    if (storage.getLocations().stream().anyMatch(l -> l.getAirportId().equals(form.getAirportId()))) {
        view.showError("El ID del aeropuerto ya está registrado.");
        return false;
    }

    // Validar latitud
    try {
        double lat = Double.parseDouble(form.getAirportLatitude());
        if (lat < -90 || lat > 90) {
            view.showError("La latitud debe estar entre -90 y 90.");
            return false;
        }
        if (form.getAirportLatitude().contains(".") && form.getAirportLatitude().split("\\.")[1].length() > 4) {
            view.showError("La latitud debe tener máximo 4 cifras decimales.");
            return false;
        }
    } catch (NumberFormatException e) {
        view.showError("La latitud debe ser un número válido.");
        return false;
    }

    // Validar longitud
    try {
        double lon = Double.parseDouble(form.getAirportLongitude());
        if (lon < -180 || lon > 180) {
            view.showError("La longitud debe estar entre -180 y 180.");
            return false;
        }
        if (form.getAirportLongitude().contains(".") && form.getAirportLongitude().split("\\.")[1].length() > 4) {
            view.showError("La longitud debe tener máximo 4 cifras decimales.");
            return false;
        }
    } catch (NumberFormatException e) {
        view.showError("La longitud debe ser un número válido.");
        return false;
    }

    return true;
}

    
    @Override
    public void onRegisterFlightIntent (FlightForm form){
        
        boolean isValidForm = isFlightFormValid(form);
        if(!isValidForm) {    
            return;
        }
        
        Flight flight = new Flight(
            form.getId(),
            storage.getPlaneById(form.getPlaneId()),
            storage.getLocationById(form.getDepartureLocationId()),
            storage.getLocationById(form.getScaleLocationId()),
            storage.getLocationById(form.getArrivalLocationId()),
            LocalDateTime.of(form.getYear(), form.getMonth(), form.getDay(), form.getHoursDeparture(), form.getMinutesDeparture()),
            form.getHoursDurationsArrival(),
            form.getMinutesDurationsArrival(),
            form.getHoursDurationsScale(),
            form.getMinutesDurationsScale()
        );
        
        this.storage.saveFlight(flight);    
    }

    private boolean isFlightFormValid(FlightForm form) {      
        try {
        if (
       (form.getId() == null || form.getId().trim().isEmpty()) &&
       (form.getPlaneId() == null || form.getPlaneId().trim().isEmpty()) &&
       (form.getDepartureLocationId() == null || form.getDepartureLocationId().trim().isEmpty()) &&
       (form.getArrivalLocationId() == null || form.getArrivalLocationId().trim().isEmpty()) &&
       (form.getScaleLocationId() == null || form.getScaleLocationId().trim().isEmpty()) &&
       form.getYear() == 0 &&
       form.getMonth() == 0 &&
       form.getDay() == 0 &&
       form.getHoursDeparture() == 0 &&
       form.getMinutesDeparture() == 0 &&
       form.getHoursDurationsArrival() == 0 &&
       form.getMinutesDurationsArrival() == 0 &&
       form.getHoursDurationsScale() == 0 &&
       form.getMinutesDurationsScale() == 0
   ) {
       view.showError("Debes llenar todos los campos antes de crear el vuelo.");
       return false;
   }    
        // Evitar campos completamente vacíos (null o string vacío)
        if (form.getId() == null || form.getId().trim().isEmpty() ||
            form.getPlaneId() == null || form.getPlaneId().trim().isEmpty() ||
            form.getDepartureLocationId() == null || form.getDepartureLocationId().trim().isEmpty() ||
            form.getArrivalLocationId() == null || form.getArrivalLocationId().trim().isEmpty()) {
            view.showError("Debes llenar todos los campos obligatorios del vuelo antes de crear.");
            return false;
        }

        // Validar que valores numéricos como fecha y hora sean razonables
        if (form.getYear() <= 0 || form.getMonth() <= 0 || form.getMonth() > 12 ||
            form.getDay() <= 0 || form.getDay() > 31 ||
            form.getHoursDeparture() < 0 || form.getHoursDeparture() > 23 ||
            form.getMinutesDeparture() < 0 || form.getMinutesDeparture() > 59) {
            view.showError("Valores de fecha u hora inválidos.");
            return false;
        }

        // Validar ID formato XXXYYY
        if (!form.getId().matches("^[A-Z]{3}\\d{3}$")) {
            view.showError("El ID del vuelo debe tener el formato XXXYYY.");
            return false;
        }

        if (storage.getFlights().stream().anyMatch(f -> f.getId().equals(form.getId()))) {
            view.showError("El ID del vuelo ya está registrado.");
            return false;
        }

        // Validar existencia del avión
        if (storage.getPlaneById(form.getPlaneId()) == null) {
            view.showError("El avión especificado no existe.");
            return false;
        }

        // Validar localizaciones
        if (storage.getLocationById(form.getDepartureLocationId()) == null ||
            storage.getLocationById(form.getArrivalLocationId()) == null) {
            view.showError("Las localizaciones de salida y llegada deben existir.");
            return false;
        }

        // Validar escala
        boolean hasScale = form.getScaleLocationId() != null && !form.getScaleLocationId().isEmpty();
        if (hasScale) {
            if (storage.getLocationById(form.getScaleLocationId()) == null) {
                view.showError("La localización de escala especificada no existe.");
                return false;
            }
        } else {
            if (form.getHoursDurationsScale() != 0 || form.getMinutesDurationsScale() != 0) {
                view.showError("Si no hay escala, el tiempo de escala debe ser 00:00.");
                return false;
            }
        }

        // Validar fecha de salida
        LocalDateTime departure = LocalDateTime.of(
            form.getYear(), form.getMonth(), form.getDay(),
            form.getHoursDeparture(), form.getMinutesDeparture()
        );

        // Validar duración del vuelo
        if (form.getHoursDurationsArrival() == 0 && form.getMinutesDurationsArrival() == 0) {
            view.showError("La duración del vuelo debe ser mayor que 00:00.");
            return false;
        }

        return true;

    
    } catch (Exception e) {
        e.printStackTrace(); // para ver detalles en consola si hay errores
        view.showError("Error general al validar el formulario de vuelo.");
        return false;
    }
}



    @Override
    public void onSavedFlight() {
        this.view.clearFlightForm();
        this.view.updateFlightLists(this.storage.getFlights());
    }
    
    @Override
    public void onOpenAddToFlightView() {
        String passengerId = String.valueOf(storage.getSelectedPassengerId());
        this.view.setPassengerIdInAddToFlight(passengerId);
    }

    @Override
    public void onSelectedPassengerId(String passengerId) {        
        boolean validPassenger = this.storage.isValidPasengerId(passengerId);
        if(!validPassenger) {
            return;
        }
        
        this.storage.setSelectedPassengerId(Integer.parseInt(passengerId));
    }

    @Override
    public void onSetPassengerId() {
        this.view.setPassengerIdInAddToFlight(String.valueOf(this.storage.getSelectedPassengerId()));
    }
}
