/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class CinemaServices {
    
    @Autowired
    CinemaPersitence cps;
    
    
    public void addNewCinema(Cinema c){
        
    }
    
    public Set<Cinema> getAllCinemas(){
        return cps.getAllCinema();
    }
    
    /**
     * 
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    public Cinema getCinemaByName(String name) throws CinemaException, CinemaPersistenceException{
        return cps.getCinema(name);
    }
    
    
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException{
        cps.buyTicket(row, col, cinema, date, movieName);
    }
    
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaPersistenceException {
        return cps.getFunctionsbyCinemaAndDate(cinema, date);
    }
    
    public List<Movie> getFilteredByGender(Cinema cinema, String date, int genero) throws CinemaPersistenceException{
        return cps.getFilteredByGender(cinema, date, genero);
    }
    
    public List<Movie> getFilteringByAvailability(String cinema, String date, int seat) throws CinemaPersistenceException{
        return cps.getFilteringByAvailability(cinema, date, seat);
    }
    
    public CinemaFunction getCinemaByNameDateMovieName(String cinema, String date, String moviename) throws CinemaPersistenceException{
        return cps.getCinemaByNameDateMovieName(cinema, date, moviename);
    }

    public void setFunccionCIne(CinemaFunction cinemaFn, String name) {
        cps.setFunccionCIne(cinemaFn,name);
    }

    public void ActualizarFuncion(CinemaFunction cinemaFn, String name) {
        cps.ActualizarFuncion(cinemaFn, name);
    }
    
}