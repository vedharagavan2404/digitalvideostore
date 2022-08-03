package com.example.digitalvideostoreapi.services;

import com.example.digitalvideostoreapi.models.Movie;
import com.example.digitalvideostoreapi.models.MovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MovieShowService
{
    @Autowired
    private MovieShowRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertIntoMovies(Movie movie)
    {
        repository.insert(movie);
    }

    public List<Movie> getMovies()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("movie"));

        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getShows()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("show"));

        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getMoviesWithTitle(String t) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(t));

        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getFeaturedMovies(Boolean fm) {
        Query query = new Query();
        query.addCriteria(Criteria.where("featuredMovie").is(fm));

        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getFeaturedShows(Boolean fs)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("featuredShow").is(fs));

        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public Optional<Movie> getAMovieOrShow(String id) throws Exception{
        Optional<Movie> movie =repository.findById(id);

        if(!movie.isPresent())
        {
            throw new Exception("Movie/Show with id "+ id + " is not found");
        }
        return movie;

    }

    public Optional<Movie> deleteAMovieOrShow(String id) throws Exception{
        Optional<Movie> movie = repository.findById(id);
        repository.deleteById(id);

        if(!movie.isPresent())
        {
            throw new Exception("Movie/Show with id "+ id + " is not found");
        }

        return movie;
    }

    public Movie updateRentPrice(String id, Movie newMovieData) throws Exception {
        Optional<Movie> movie = repository.findById(id);

        if(!movie.isPresent())
        {
            throw new Exception("Movie/Show with id "+ id + " is not found");
        }
        //System.out.println(newMovieData);

        if(newMovieData.getRent_amount()==0)
        {
          throw new Exception("Please enter the rent amount");
        }

        movie.get().setRent_amount(newMovieData.getRent_amount());

        Movie updatedMovie = repository.save(movie.get());
        return updatedMovie;
    }
}
