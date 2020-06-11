package dto;

import entities.Address;

/**
 *
 * @author Nina
 */
public class AddressDTO {
    
    private String street;
    private String city;
    private String zip;

    public AddressDTO() {
    }

    public AddressDTO(String street, String city, String zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
    
    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.zip = address.getZip();
    }

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
    
}
