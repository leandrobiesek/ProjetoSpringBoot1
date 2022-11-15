package com.api.piorfilme.repositories;

import com.api.piorfilme.models.ProducerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerModel, Long> {

    @Query(value = "SELECT * \n" +
            "FROM TB_PRODUCER P \n" +
            "LEFT JOIN REL_PRODUCER_MOVIE RPM ON RPM.PRODUCER = P.ID\n" +
            "LEFT JOIN TB_MOVIE M ON M.ID = RPM.MOVIE\n" +
            "WHERE M.WINNER = TRUE ORDER BY M.ANO ASC, P.ID ASC",
            nativeQuery = true
    )
    List<ProducerModel> findAllWinnerProducersAndYears();
}
