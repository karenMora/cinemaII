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
        
        String functionDateA = "2019-03-28 15:30";
        List<CinemaFunction> functionsA= new ArrayList<>();
        CinemaFunction funct1A = new CinemaFunction(new Movie("Wifi Ralph","Kids"),functionDateA);
        CinemaFunction funct2A = new CinemaFunction(new Movie("Minions","Kids"),functionDateA);
        CinemaFunction funct3A = new CinemaFunction(new Movie("Totoro","Kids"),functionDateA);
        CinemaFunction funct4A = new CinemaFunction(new Movie("Frozen","Kids"),functionDateA);
        functionsA.add(funct1A);
        functionsA.add(funct2A);
        functionsA.add(funct3A);
        functionsA.add(funct4A);
        Cinema cA=new Cinema("procinal",functionsA);
        cinemas.put("procinal", cA);
        
        String functionDateB = "2019-09-03 15:30";
        List<CinemaFunction> functionsB= new ArrayList<>();
        CinemaFunction funct1B = new CinemaFunction(new Movie("El regreso de Mary Poppins","Action"),functionDateB);
        CinemaFunction funct2B = new CinemaFunction(new Movie("Mulan","Action"),functionDateB);
        functionsB.add(funct1B);
        functionsB.add(funct2B);
        Cinema cB=new Cinema("Cine Colombia",functionsB);
        cinemas.put("Cine Colombia", cB);
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
