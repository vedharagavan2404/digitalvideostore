package com.example.digitalvideostoreapi.controllers;

import com.example.digitalvideostoreapi.CustomizedResponse;
import com.example.digitalvideostoreapi.models.Movie;
import com.example.digitalvideostoreapi.services.MovieShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "https://digital-video-store-frontend.herokuapp.com")
@RestController
public class MovieShowController
{
    @Autowired
    private MovieShowService service;

    @GetMapping("/allvideos")
    public ResponseEntity<Movie> getallVideos()
    {
        return new ResponseEntity(service.getAllVideos(), HttpStatus.OK);
    }


    @GetMapping("/movies")
    public ResponseEntity<Movie> getmovies()
    {
        //var customizedResponse = new CustomizedResponse("A list of movies", service.getMovies());
        return new ResponseEntity(service.getMovies(), HttpStatus.OK);
    }

    @GetMapping("/shows")
    public ResponseEntity<Movie> getshows()
    {
        //var customizedResponse = new CustomizedResponse("A list of movies with rating :", service.getMoviesWithRating(r));
        return new ResponseEntity(service.getShows(), HttpStatus.OK);
    }

    @GetMapping("/movies/title")
    public ResponseEntity<Movie> getmoviesByTitle(@RequestParam(value="titl") String t)
    {
        //var customizedResponse = new CustomizedResponse("A list of movies with rating :", service.getMoviesWithRating(r));
        return new ResponseEntity(service.getMoviesWithTitle(t), HttpStatus.OK);
    }

    @GetMapping("/movies/featuredMovie")
    public ResponseEntity<Movie> getfeaturedmovies(@RequestParam(value="featured") Boolean fm)
    {
        //var customizedResponse = new CustomizedResponse("A list of movies with rating :", service.getMoviesWithRating(r));
        return new ResponseEntity(service.getFeaturedMovies(fm), HttpStatus.OK);
    }

    @GetMapping("/movies/featuredMovieWithTitle")
    public ResponseEntity<Movie> getFeaturedMoviesWithTitle(@RequestParam(value="titl") String t)
    {
        //var customizedResponse = new CustomizedResponse("A list of movies with rating :", service.getMoviesWithRating(r));
        return new ResponseEntity(service.getFeaturedMoviesWithTitle(t), HttpStatus.OK);
    }

    @GetMapping("/shows/featuredShow")
    public ResponseEntity<Movie> getfeaturedshows(@RequestParam(value="featured") Boolean fs)
    {
        //var customizedResponse = new CustomizedResponse("A list of movies with rating :", service.getMoviesWithRating(r));
        return new ResponseEntity(service.getFeaturedShows(fs), HttpStatus.OK);
    }

    @GetMapping("/movies/featuredShowWithTitle")
    public ResponseEntity<Movie> getFeaturedShowsWithTitle(@RequestParam(value="titl") String t)
    {
        //var customizedResponse = new CustomizedResponse("A list of movies with rating :", service.getMoviesWithRating(r));
        return new ResponseEntity(service.getFeaturedShowsWithTitle(t), HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getAMovie(@PathVariable("id") String id)
    {

        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Movie with id " + id , Collections.singletonList(service.getAMovieOrShow(id)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteAMovie(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;

        try {
            customizedResponse = new CustomizedResponse("Movie with id " + id +" is deleted", Collections.singletonList(service.deleteAMovieOrShow(id)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/movies/{id}" , consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updatemovie(@PathVariable("id") String id, @RequestBody Movie newMovie){

        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Movie with the id "+ id + " is updated as requested", Collections.singletonList(service.updateRentPrice(id, newMovie)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);

    }

    @PostMapping(value="/movies", consumes ={
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addmovie(@RequestBody Movie movie)
    {
        service.insertIntoMovies(movie);
        return new ResponseEntity(movie, HttpStatus.OK);
    }
}
