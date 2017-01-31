/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identidades;

/**
 *
 * @author Emilio
 */
public class Country {
    private int countryId;
    private String countryName;
    private String countrySlug;

    public Country(int countryId, String contryName, String countrySlug) {
        this.countryId = countryId;
        this.countryName = contryName;
        this.countrySlug = countrySlug;
    }
    
    public Country(String contryName, String countrySlug) {
        this.countryName = contryName;
        this.countrySlug = countrySlug;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setContryName(String contryName) {
        this.countryName = contryName;
    }

    public String getCountrySlug() {
        return countrySlug;
    }

    public void setCountrySlug(String countrySlug) {
        this.countrySlug = countrySlug;
    }

    @Override
    public String toString() {
        return this.countryName;
    }
    
    
}
