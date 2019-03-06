/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author cristian
 */
@Component("cps")
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);
        cinemas.put("cinemaX", c);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        CinemaFunction funcion=null;
        for (CinemaFunction funcionCine: cinemas.get(cinema).getFunctions()){
            if(funcionCine.getMovie().getName().equals(movieName) && funcionCine.getDate().equals(date)){
                funcion=funcionCine;
                break;
            }
        }
        funcion.buyTicket(row, col);
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        List<CinemaFunction> funciones=new ArrayList<>();
        for (CinemaFunction funcionCine: cinemas.get(cinema).getFunctions()){
            if( funcionCine.getDate().equals(date)){
                funciones.add(funcionCine);
            }
        }
        return funciones;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        return cinemas.get(name);
    }

    @Override
    public List<Movie> getFilteredByGender(Cinema cinema, String date, int genero) throws CinemaPersistenceException {
        List<Movie> pelis=new ArrayList<>();
        List<CinemaFunction> funciones=new ArrayList<>();
        Cinema cine;
        for(CinemaFunction funcion: funciones){
            if(funcion.getDate().equals(date) && funcion.getMovie().getGenre().equals(genero)){
                pelis.add(funcion.getMovie());
            }
        }
        return pelis;
    }

    @Override
    public List<Movie> getFilteringByAvailability(String cinema, String date, int seat) throws CinemaPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
