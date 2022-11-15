package com.api.piorfilme.models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_MOVIE")
public class MovieModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ano;

    @Column(nullable = false)
    private String name;

    @Column
    private Boolean winner;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    List<StudioMovieModel> studioMovieModelList;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    List<ProducerMovieModel> producerMovieModelList;

    public MovieModel() {

    }

    public MovieModel(Long year, String name, String winner) {
        this.ano = year;
        this.name = name;
        this.winner = winner.equals("yes") ? Boolean.TRUE : Boolean.FALSE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public List<StudioMovieModel> getStudioMovieModelList() {
        if (studioMovieModelList == null) {
            studioMovieModelList = new ArrayList<>();
        }
        return studioMovieModelList;
    }

    public void setStudioMovieModelList(List<StudioMovieModel> studioMovieModelList) {
        this.studioMovieModelList = studioMovieModelList;
    }

    public List<ProducerMovieModel> getProducerMovieModelList() {
        if (producerMovieModelList == null) {
            producerMovieModelList = new ArrayList<>();
        }
        return producerMovieModelList;
    }

    public void setProducerMovieModelList(List<ProducerMovieModel> producerMovieModelList) {
        this.producerMovieModelList = producerMovieModelList;
    }
}
