package com.api.piorfilme.services;

import com.api.piorfilme.models.ProducerModel;
import com.api.piorfilme.models.StudioModel;
import com.api.piorfilme.repositories.ProducerRepository;
import com.api.piorfilme.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StudioService extends GenericCrudServiceImpl<StudioModel,Long> {

    final StudioRepository studioRepository;

    public StudioService(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    protected JpaRepository<StudioModel, Long> getRepository() {
        return studioRepository;
    }
}