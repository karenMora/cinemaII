/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cristian
 */
@Controller
@RequestMapping("/cinemas")
@Service
@RestController
public class CinemaAPIController {
    
    @Autowired
    CinemaServices cinemaServices;
    
    //el conjunto de todos los cines
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> GetSetAllCinemas(){
        try{
            return new ResponseEntity<>(cinemaServices.getAllCinemas(),HttpStatus.ACCEPTED);
        }catch(Exception ex){
           Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<>("Error al encontrar los cines",HttpStatus.NOT_FOUND);
        }
    }
    
    // todas las funciones del cine cuyo nombre sea {name}.
    // Si no existe dicho cine,
    // se debe responder con el código de error HTTP 404.
    @GetMapping("/{name}")
    public ResponseEntity<?> getCinemaByName(@PathVariable String name){
        try{
            return new ResponseEntity<>(cinemaServices.getCinemaByName(name),HttpStatus.ACCEPTED);
        }catch(Exception ex){
           Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<>("404 NOT FOUND",HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{name}/{date}")
    public ResponseEntity<?> getlistCinemaByNameAndDate(@PathVariable String name,@PathVariable String date){
        try{
            return new ResponseEntity<>(cinemaServices.getFunctionsbyCinemaAndDate(name, date),HttpStatus.ACCEPTED);
        }catch(Exception ex){
           Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<>("404 NOT FOUND",HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping("/{name}/{date}/{moviename}")
    public ResponseEntity<?> getCinemaByNameDateMovieName(@PathVariable String name,@PathVariable String date,@PathVariable String moviename){
        try{
            return new ResponseEntity<>(cinemaServices.getCinemaByNameDateMovieName(name,date,moviename),HttpStatus.ACCEPTED);
        }catch(Exception ex){
           Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<>("404 NOT FOUND",HttpStatus.NOT_FOUND);
        }
    }
    
}
