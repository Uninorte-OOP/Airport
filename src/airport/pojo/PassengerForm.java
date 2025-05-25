/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.pojo;

import java.time.LocalDate;

/**
 *
 * @author miguel
 */
public class PassengerForm {
    String id;
    String firstname;
    String lastname;
    String year;
    String month;
    String day;
    String phoneCode;
    String phone;
    String country;
    
    public PassengerForm(String id, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.year = year;
        this.month = month;
        this.day = day;
        this.phoneCode = phoneCode;
        this.phone = phone;
        this.country = country;
    }

    public PassengerForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }
}
