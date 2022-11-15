package com.api.piorfilme.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "REL_PRODUCER_MOVIE")
public class ProducerMovieModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie")
    private MovieModel movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer")
    private ProducerModel producer;

    public ProducerMovieModel() {

    }

    public ProducerMovieModel(MovieModel movie, ProducerModel producer) {
        this.movie = movie;
        this.producer = producer;
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

    public ProducerModel getProducer() {
        return producer;
    }

    public void setProducer(ProducerModel producer) {
        this.producer = producer;
    }
}
