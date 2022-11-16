package com.api.piorfilme.services;

import com.api.piorfilme.dtos.PrizeIntervalDTO;
import com.api.piorfilme.dtos.PrizeIntervalReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationService {

    private final ProducerService producerService;

    public ApplicationService(ProducerService producerService) {
        this.producerService = producerService;
    }

    public PrizeIntervalReturnDTO getPrizeIntervals() {
        var returnDTO = new PrizeIntervalReturnDTO();
        Long minPrevYear, minFollowYear, maxPrevYear, maxFollowYear;
        Long auxStoredInterval;
        Long interval;

        HashMap<String, List<Long>> winnerProducersAndYears = this.producerService.getWinnerProducersAndYears();

        for (var producerAndYears: winnerProducersAndYears.entrySet()) {

            //Busca os anos dos menores e dos maiores intervalos
            maxPrevYear = producerAndYears.getValue().get(0);
            maxFollowYear = producerAndYears.getValue().get(1);
            minPrevYear = producerAndYears.getValue().get(0);
            minFollowYear = producerAndYears.getValue().get(1);


            for (int i = 0; i < producerAndYears.getValue().size() - 1; i++) {
                minPrevYear = producerAndYears.getValue().get(i);
                minFollowYear = producerAndYears.getValue().get(i + 1);
                if (returnDTO.getMin().size() == 0) {
                    this.insertIntoList(returnDTO.getMin(), producerAndYears.getKey(), minPrevYear, minFollowYear);
                    continue;
                } else {
                    if (returnDTO.getMin().get(0).getInterval() >= (minFollowYear - minPrevYear)) {

                        if (returnDTO.getMin().get(0).getInterval() > (minFollowYear - minPrevYear)) {
                            returnDTO.getMin().clear();
                        }
                        this.insertIntoList(returnDTO.getMin(), producerAndYears.getKey(), minPrevYear, minFollowYear);

                    }
                }
            }

            for (int i = 0; i < producerAndYears.getValue().size() - 1; i++) {
                maxPrevYear = producerAndYears.getValue().get(i);
                maxFollowYear = producerAndYears.getValue().get(i + 1);
                if (returnDTO.getMax().size() == 0) {
                    this.insertIntoList(returnDTO.getMax(), producerAndYears.getKey(), maxPrevYear, maxFollowYear);
                    continue;
                } else {
                    if (returnDTO.getMax().get(0).getInterval() <= (maxFollowYear - maxPrevYear)) {
                        if (returnDTO.getMax().get(0).getInterval() < (maxFollowYear - maxPrevYear)) {
                            returnDTO.getMax().clear();
                        }
                        this.insertIntoList(returnDTO.getMax(), producerAndYears.getKey(), maxPrevYear, maxFollowYear);
                    }
                }
            }
        }
        return returnDTO;
    }

    private void insertIntoList(List<PrizeIntervalDTO> list, String prodName, Long minValue, Long maxValue) {
        list.add(new PrizeIntervalDTO(
                prodName,
                maxValue - minValue,
                minValue,
                maxValue
        ));
    }
}
