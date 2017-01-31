/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import identidades.Country;

/**
 *
 * @author Emilio
 */
public interface CountryRepository {
    void save(Country country);
    Country getCountry(int id);
    List<Country> getCountries();
    int getCountryLastId();
}
