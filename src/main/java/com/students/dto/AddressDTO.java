package com.students.dto;

/**
 * @author Shakti Shekhawat
 * @version 1.0 
 * AddressDTO class is interface for mapping all the address information of students
 */
public class AddressDTO {

    private Long id;

    
    /**
     * Student address street name
     */
    private String addressLine1;
    /**
     * Student address street name with extra options
     */
    private String addressLine2;
    /**
     * Student city 
     */
    private String city;
    /**
     * Student country
     */
    private String country;
    /**
     * Student postal code 
     */
    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
