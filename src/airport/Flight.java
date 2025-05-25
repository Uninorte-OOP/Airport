/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class Flight {
    
    private final String id;
    private ArrayList<Passenger> passengers;
    private Plane plane;
    private Location departureLocation;
    private Location scaleLocation;
    private Location arrivalLocation;
    private LocalDateTime departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;
    
    


    public Flight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation, 
            LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.scaleLocation = scaleLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;
        
        this.plane.addFlight(this);
    }

    public String getId() {
        return id;
    }

    public Plane getPlane() {
        return plane;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getScaleLocation() {
        return scaleLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public int getHoursDurationArrival() {
        return hoursDurationArrival;
    }

    public int getMinutesDurationArrival() {
        return minutesDurationArrival;
    }

    public int getHoursDurationScale() {
        return hoursDurationScale;
    }

    public int getMinutesDurationScale() {
        return minutesDurationScale;
    }
    


    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setDepartureLocation(Location departureLocation) {
        this.departureLocation = departureLocation;
    }

    public void setScaleLocation(Location scaleLocation) {
        this.scaleLocation = scaleLocation;
    }

    public void setArrivalLocation(Location arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public void setHoursDurationArrival(int hoursDurationArrival) {
        this.hoursDurationArrival = hoursDurationArrival;
    }

    public void setMinutesDurationArrival(int minutesDurationArrival) {
        this.minutesDurationArrival = minutesDurationArrival;
    }

    public void setHoursDurationScale(int hoursDurationScale) {
        this.hoursDurationScale = hoursDurationScale;
    }

    public void setMinutesDurationScale(int minutesDurationScale) {
        this.minutesDurationScale = minutesDurationScale;
    }

    public String getPlaneId() {
        return plane.getId();
    }

    public String getDepartureLocationId() {
        return departureLocation.getId();
    }

    public String getArrivalLocationId() {
        return arrivalLocation.getId();
    }

    public String getScaleLocationId() {
        return scaleLocation != null ? scaleLocation.getId() : null;
    }
    
    public int getYear() {
    return departureDate.getYear();
}

    public int getMonth() {
        return departureDate.getMonthValue(); // 1 a 12
    }

    public int getDay() {
        return departureDate.getDayOfMonth();
    }

    public int getHour() {
        return departureDate.getHour();
    }

    public int getMinutes() {
        return departureDate.getMinute();
    }
    
    public int getHoursDurationsArrival() {
    return hoursDurationArrival;
}

    public int getMinutesDurationsArrival() {
        return minutesDurationArrival;
    }

    public int getHoursDurationsScale() {
        return hoursDurationScale;
    }

    public int getMinutesDurationsScale() {
        return minutesDurationScale;
    }

    Object calculateArrivalDate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getNumPassengers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void addPassenger(Passenger passenger) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void delay(int hours, int minutes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
