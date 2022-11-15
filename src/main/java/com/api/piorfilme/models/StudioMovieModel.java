package com.api.piorfilme.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "REL_STUDIO_MOVIE")
public class StudioMovieModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie")
    private MovieModel movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studio")
    private StudioModel studio;

    public StudioMovieModel() {

    }

    public StudioMovieModel(MovieModel movie, StudioModel studio) {
        this.movie = movie;
        this.studio = studio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    public StudioModel getStudio() {
        return studio;
    }

    public void setStudio(StudioModel studio) {
        this.studio = studio;
    }
}
