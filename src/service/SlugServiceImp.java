/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.text.Normalizer;

/**
 *
 * @author Emilio
 */
public class SlugServiceImp implements SlugService {
    
    @Override
    public String getWord(String texto) {
        texto = texto.toLowerCase();
        if(texto.contains("-")){
            texto = texto.replaceAll(" ", "");
        }
        if(texto.contains("/")){
            texto = texto.replace("/", "");
        }
        if(texto.contains("[")){
            texto = texto.replace("[", "");
        }
        if(texto.contains("]")){
            texto = texto.replace("]", "");
        }
        if(texto.contains("(")){
            texto = texto.replace("(", "");
        }
        if(texto.contains(")")){
                texto = texto.replace("(", "");
        }
        if(texto.contains("{")){
            texto = texto.replace("{", "");
        }
        if(texto.contains("}")){
                texto = texto.replace("}", "");
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

    @Override
    public boolean isSlug(String slug) {
        if(isMayus(slug)){
            return false;
        }
        if(slug.contains(" ")){
            return false;
        }
        if(slug.contains("[")){
            return false;    
        }
        if(slug.contains("/")){
            return false;
        }
        if(slug.contains("]")){
            return false;
        }
        if(slug.contains("(")){
            return false;
        }
        if(slug.contains(")")){
            return false;
        }
        if(slug.contains("{")){
            return false;
        }
        if(slug.contains("}")){
            return false;
        }
        if(slug.contains("'")){
            return false;
        }
        if(slug.contains("’")){
            return false;
        }
        if(slug.contains(".")){
            return false;
        }
        final String text = Normalizer.normalize(slug, Normalizer.Form.NFD);
        //if(!isLetterOrScript(slug)){
            //return false;
        //}
        if(text.contains("[\\p{InCombiningDiacriticalMarks}]")){
            return false;
        }
        return true;
    }
    
    private boolean isMayus(String slug){
        int c = 0;
        boolean mayus = false;
        while(c < slug.length()){
            char letra = slug.charAt(c);
            if(Character.isUpperCase(letra)){
                mayus = true;
            }
            c++;
        }
        return mayus;
    }
    
    /*private boolean isLetterOrScript(String slug){
        int c = 0;
        boolean letter = false;
        while(c < slug.length()){
            char letra = slug.charAt(c);
            if(Character.isLetter(letra) || letra == '-'){
                letter = true;
            }
            else{
                return false;
            }
            c++;
        }
        return letter;
    }
    */
}
