/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emilio
 */
public class Lectura {
    List <String> paises = new ArrayList<String>();
    public List<String> toFileToArray(File archivo){
        FileReader fr = null;
        BufferedReader br = null;
        try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
        // archivo = new File ("C:\\archivo.txt");
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
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
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
    
    public void crearFichero(List<String> paises) throws IOException{
        String ruta = "C:\\Taller\\scriptpaises.sql";
        File archivo = new File(ruta);
        BufferedWriter bw;
        
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            for(String pais : paises){
                bw.write(pais + " \n");
            }
        } else {
            archivo.createNewFile();
            bw = new BufferedWriter(new FileWriter(archivo));
            for(String pais : paises){
                bw.write(pais + "\n");
            }
            
        }
        bw.close();
    }
    
    public List<String> getSlug(List<String> paises){
        List<String> paisesNormal = new ArrayList<>();
        String sql = "INSERT INTO PAISES(nombre,slug) VALUES";
        for(String texto : paises ){
            texto = sql + "(" + texto + "," + getWord(texto) + ")" ;
            paisesNormal.add(texto);
        }
        return paisesNormal;
    }
    
    public String getWord(String texto){
        texto = texto.toLowerCase();
        if(texto.contains("-")){
            texto = texto.replaceAll(" ", "");
        }
        if(texto.contains("[")){
            texto = texto.replace("[", "");
            if(texto.contains("]")){
                texto = texto.replace("]", "");
            }
        }
        if(texto.contains("'")){
            texto = texto.replace("'", "");
        }
        if(texto.contains("’")){
            texto = texto.replace("’", "");
        }
        if(texto.contains(".")){
            texto = texto.replace(".", "");
        }
        texto = texto.replaceAll(" ","-");
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
    
}
