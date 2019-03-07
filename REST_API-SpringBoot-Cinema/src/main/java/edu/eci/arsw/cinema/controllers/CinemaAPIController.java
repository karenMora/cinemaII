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
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.X;
//import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
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
    
    //un cliente http pueda registrar una nueva función a un determinado cine 
    @RequestMapping(value="{name}’", method = RequestMethod.POST)
    public ResponseEntity<?> RegistrarNuevaFuncionCine(@RequestBody String name){
        try {
            //registrar dato
            //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http:localhost8080/cinema/procinal -d '{{"movie":{"name":"Frozen","genre":"Kids"},"seats":[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]],"date":"2019-03-28 15:30"}}'
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al Registrar la funcion",HttpStatus.FORBIDDEN);
        }
    }
    
    
}
