/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.pojo;

/**
 *
 * @author miguel
 */
public class LocationForm {
    String airportId;
    String airportName;
    String airportCity;
    String airportCountry;
    String airportLatitude;
    String airportLongitude;
    
    public LocationForm(String airportId, String airportName, String airportCity, String airportCountry, String airportLatitude, String airportLongitude) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
        this.airportLatitude = airportLatitude;
        this.airportLongitude = airportLongitude;
    }

    public String getAirportId() {
        return airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public String getAirportCountry() {
        return airportCountry;
    }

    public String getAirportLatitude() {
        return airportLatitude;
    }

    public String getAirportLongitude() {
        return airportLongitude;
    }
       
}
