package com.acme.rantotta.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
public class Address {

    @NotNull
    @Length(min=4)
    private String street;
    
    @NotNull
    private String city;
    
    @NotNull
    private String zip;
    
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    @Override
    public String toString() {
        return "Address [street=" + street + ", city=" + city + ", zip=" + zip + "]";
    }
}
