package com.acme.rantotta.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Address {

    @NotEmpty
    @Length(min=4)
    private String street;
    
    @NotEmpty
    private String city;
    
    @Pattern(regexp="[0-9]{4}", message="should be 4 numbers")
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
