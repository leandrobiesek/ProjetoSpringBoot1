package com.api.piorfilme.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PRODUCER")
public class ProducerModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    List<ProducerMovieModel> producerMovieModelList;

    public ProducerModel() {}

    public ProducerModel(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
