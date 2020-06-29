/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.RegisterInDB;
import model.Actor;
import model.Film;

/**
 *
 * @author Carlos Rodriguez
 */
public class FilmController extends RegisterInDB{
    
    public void registerFilmInDB(Film film, Actor[] actors){
       super.registerFilm(film, actors);
    }
}
