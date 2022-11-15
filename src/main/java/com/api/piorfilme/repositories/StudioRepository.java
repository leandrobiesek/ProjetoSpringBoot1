package com.api.piorfilme.repositories;

import com.api.piorfilme.models.StudioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<StudioModel, Long> {
}
