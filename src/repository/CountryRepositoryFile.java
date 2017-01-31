/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import identidades.Country;
import service.SlugService;

/**
 *
 * @author Emilio
 */
public class CountryRepositoryFile implements CountryRepository {
    //private File archivo;
    private final File archivoFinal = new File("C:\\Users\\claro\\Documents\\NetBeansProjects\\TallerProgramacion\\src\\data\\scriptpaises.txt");
    SlugService slugservice;
    
    public CountryRepositoryFile(SlugService slugservice){
        this.slugservice = slugservice;
    }
    
    @Override
    public void save(Country country) {
        if(slugservice.isSlug(country.getCountrySlug())){
            try{
                BufferedWriter bw;
                if(!archivoFinal.exists()){
                    archivoFinal.createNewFile();
                }
                if(!isEmpty(archivoFinal)){
                    bw = new BufferedWriter(new FileWriter(archivoFinal,true));
                    bw.write(country.getCountryId() + ";" + country.getCountryName() + ";" + country.getCountrySlug() + "\n");
                }
                else{
                    bw = new BufferedWriter(new FileWriter(archivoFinal));
                    bw.write(country.getCountryId() + ";" + country.getCountryName() + ";" + country.getCountrySlug() + "\n");
                }                
                bw.close();
            }
            catch(IOException exc){
                exc.printStackTrace();
            }  
        }
        
    }

    @Override
    public Country getCountry(int id) {
        Country country = null;
        FileReader fr = null;
        BufferedReader br;
        try {
            fr = new FileReader (archivoFinal);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                String [] word = linea.split(";");
                if(id == Integer.parseInt(word[0])){
                    country = new Country(Integer.parseInt(word[0]),word[1],word[2]);
                }
            }   
        }
        catch(Exception e){
         e.printStackTrace();
        }
        finally{
            try{                    
                if( null != fr ){   
                fr.close();     
                }                  
            }
            catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        return country;
    }

    @Override
    public List<Country> getCountries() {
        List <Country> countries = new ArrayList<Country>();
        FileReader fr = null;
        BufferedReader br;
        try {
            if(archivoFinal.exists()){
                fr = new FileReader (archivoFinal);
                br = new BufferedReader(fr);
                String linea;
                while((linea=br.readLine())!=null){
                    String [] word = linea.split(";");
                    Country country = new Country(Integer.parseInt(word[0]),word[1],word[2]);
                    countries.add(country);
                }
            }
        }
        catch(Exception e){
         e.printStackTrace();
        }
        finally{
            try{                    
                if( null != fr ){   
                fr.close();     
                }                  
            }
            catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        return countries;
    }
    
    private boolean isEmpty(File archivo){
        boolean value = false;
        try{
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            if(br.readLine() == null){
                value = true;
            }
            else{
                value = false;
            }
        }
        catch(IOException exc){
            exc.printStackTrace();
        }
        return value;
    }
    
    /*
    public void setArchivo(File archivo){
        this.archivo = archivo;
    }
    */
    
    @Override
    public int getCountryLastId(){
        int id = 0;
        String lastLine = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            if(archivoFinal.exists()){
                fr = new FileReader (archivoFinal);
                br = new BufferedReader(fr);
                String linea = br.readLine();
                while(linea != null){
                    lastLine = linea;
                    linea = br.readLine();
                }
                if(lastLine != null){
                    String [] chainPart = lastLine.split(";");
                    id = Integer.parseInt(chainPart[0]);
                
                }
                else{
                    id = 0;
                }
            }
            else{
                id = 0;
            }
        }
        catch(Exception e){
         e.printStackTrace();
        }
        finally{
            try{                    
                if( null != fr ){   
                fr.close();     
                }                  
            }
            catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        return id;
    }
        
    /*
    public List<String> readCountry(){
        List <String> paises = new ArrayList<String>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null){
             paises.add(linea);
         }   
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return paises;
    }
    */
}
