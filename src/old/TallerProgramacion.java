/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old;

import java.io.File;
import java.io.IOException;
import java.util.List;
import service.SlugService;
import service.SlugServiceImp;

/**
 *
 * @author Emilio
 */
public class TallerProgramacion {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*File archivo = new File("C:\\Taller\\countries.txt");
        Lectura lec = new Lectura();
        List<String> paises = lec.getSlug(lec.toFileToArray(archivo));
        lec.crearFichero(paises); 
        */
        SlugService sl = new SlugServiceImp();
        System.out.println(sl.isSlug("democratic-republic-of-china"));
    }
    
    
}
