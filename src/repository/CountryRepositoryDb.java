/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;
import conexion.Conexion;
import identidades.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import service.SlugService;

/**
 *
 * @author Mauro
 */
public class CountryRepositoryDb implements CountryRepository{
    SlugService slugservice;
    
    public CountryRepositoryDb(SlugService slugservice){
        this.slugservice = slugservice;
    }
    
    @Override
    public void save(Country country){
        if(slugservice.isSlug(country.getCountrySlug())){
           Conexion con = new Conexion();
            try{
                Connection conex = con.Conectar();
                PreparedStatement preparar = conex.prepareStatement("INSERT INTO countries(countryid,countryname,countryslug) VALUES(?,?,?)");
                preparar.setInt(1, country.getCountryId());
                preparar.setString(2, country.getCountryName());
                preparar.setString(3, country.getCountrySlug());
                preparar.execute();
                conex.close();
            }
            catch(SQLException exc){
                JOptionPane.showMessageDialog(null, exc.getMessage(), "WARNING" , JOptionPane.ERROR_MESSAGE);
            } 
        }
        
    }
    
    @Override
    public Country getCountry(int id){
        Conexion con = new Conexion();
        Country country = null;
        try{
            Connection conex = con.Conectar();
            PreparedStatement preparar = conex.prepareCall("SELECT * FROM countries WHERE countryid =?");
            preparar.setInt(1, id);
            ResultSet rs = preparar.executeQuery();
            if(rs.next()){
                country = new Country(rs.getInt(1),rs.getString(2),rs.getString(3));
            }
            else{
                JOptionPane.showMessageDialog(null,"PAÍS NO ENTRADO, PORFAVOR INGRESE ID CORRECTA","COUNTRY NOT FOUND",JOptionPane.WARNING_MESSAGE);
            }
            conex.close();
        }
        catch(SQLException exc){
            JOptionPane.showMessageDialog(null, exc.getMessage(), "WARNING" , JOptionPane.ERROR_MESSAGE);
        }
        return country;
    }
    
    @Override
    public List<Country> getCountries(){
        Conexion con = new Conexion();
        List<Country> countries = new ArrayList<Country>();
        try{
            Connection conex = con.Conectar();
            PreparedStatement preparar = conex.prepareCall("SELECT * FROM countries");
            ResultSet rs = preparar.executeQuery();
            while(rs.next()){
                Country country = new Country(rs.getInt(1),rs.getString(2),rs.getString(3));
                countries.add(country);
            }
            conex.close();
        }
        catch(SQLException exc){
            JOptionPane.showMessageDialog(null, exc.getMessage(),"PELIGRO", JOptionPane.ERROR_MESSAGE);
        }
        return countries;
    }
    
    @Override
    public int getCountryLastId(){
        Conexion con = new Conexion();
        int id = 0;
        try{
            Connection conex = con.Conectar();
            PreparedStatement preparar = conex.prepareCall("SELECT countryid FROM countries");
            ResultSet rs = preparar.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            conex.close();
        }
        catch(SQLException exc){
            JOptionPane.showMessageDialog(null, exc.getMessage(), "WARNING" , JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
    
}
