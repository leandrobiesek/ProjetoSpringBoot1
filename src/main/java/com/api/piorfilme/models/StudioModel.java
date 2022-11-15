package com.api.piorfilme.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_STUDIO")
public class StudioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL)
    List<StudioMovieModel> studioMovieModelList;

    public StudioModel() {

    }

    public StudioModel(String name) {
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

    public List<StudioMovieModel> getStudioMovieModelList() {
        if (studioMovieModelList == null) {
            studioMovieModelList = new ArrayList<>();
        }
        return studioMovieModelList;
    }

    public void setStudioMovieModelList(List<StudioMovieModel> studioMovieModelList) {
        this.studioMovieModelList = studioMovieModelList;
    }
}
