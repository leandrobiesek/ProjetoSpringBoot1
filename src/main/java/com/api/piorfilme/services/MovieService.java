package com.api.piorfilme.services;
import com.api.piorfilme.models.MovieModel;
import com.api.piorfilme.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends GenericCrudServiceImpl<MovieModel,Long> {
    final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    protected JpaRepository<MovieModel, Long> getRepository() {
        return movieRepository;
    }
}
