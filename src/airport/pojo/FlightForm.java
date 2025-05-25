/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.pojo;

/**
 *
 * @author miguel
 */
public class FlightForm {
    private String id;
    private String planeId;
    private String departureLocationId;
    private String arrivalLocationId;
    private String scaleLocationId;

    private int year;
    private int month;
    private int day;
    private int hoursDeparture;
    private int minutesDeparture;

    private int hoursDurationsArrival;
    private int minutesDurationsArrival;
    private int hoursDurationsScale;
    private int minutesDurationsScale;

    public FlightForm(
            String id,
            String planeId,
            String departureLocationId,
            String arrivalLocationId,
            String scaleLocationId,
            int year,
            int month,
            int day,
            int hoursDeparture,
            int minutesDeparture,
            int hoursDurationsArrival,
            int minutesDurationsArrival,
            int hoursDurationsScale,
            int minutesDurationsScale
    ) {
        this.id = id;
        this.planeId = planeId;
        this.departureLocationId = departureLocationId;
        this.arrivalLocationId = arrivalLocationId;
        this.scaleLocationId = scaleLocationId;

        this.year = year;
        this.month = month;
        this.day = day;
        this.hoursDeparture = hoursDeparture;
        this.minutesDeparture = minutesDeparture;

        this.hoursDurationsArrival = hoursDurationsArrival;
        this.minutesDurationsArrival = minutesDurationsArrival;
        this.hoursDurationsScale = hoursDurationsScale;
        this.minutesDurationsScale = minutesDurationsScale;
    }

    public String getId() {
        return id;
    }

    public String getPlaneId() {
        return planeId;
    }

    public String getDepartureLocationId() {
        return departureLocationId;
    }

    public String getArrivalLocationId() {
        return arrivalLocationId;
    }

    public String getScaleLocationId() {
        return scaleLocationId;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHoursDeparture() {
        return hoursDeparture;
    }

    public int getMinutesDeparture() {
        return minutesDeparture;
    }

    public int getHoursDurationsArrival() {
        return hoursDurationsArrival;
    }

    public int getMinutesDurationsArrival() {
        return minutesDurationsArrival;
    }

    public int getHoursDurationsScale() {
        return hoursDurationsScale;
    }

    public int getMinutesDurationsScale() {
        return minutesDurationsScale;
    }
    
    
}
