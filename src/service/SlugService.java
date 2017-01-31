/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Emilio
 */
public interface SlugService {
    String getWord(String text);
    boolean isSlug(String slug);
}
