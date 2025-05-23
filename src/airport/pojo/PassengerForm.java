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

    long id;
    String firstname;
    String lastname;
    int year;
    int month;
    int day;
    int phoneCode;
    long phone;
    String country;
    LocalDate birthDate;
    
    public PassengerForm(long id, String firstname, String lastname, int year, int month, int day, int phoneCode, long phone, String country, LocalDate birthDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.year = year;
        this.month = month;
        this.day = day;
        this.phoneCode = phoneCode;
        this.phone = phone;
        this.country = country;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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

    public int getPhoneCode() {
        return phoneCode;
    }

    public long getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    
    
}
