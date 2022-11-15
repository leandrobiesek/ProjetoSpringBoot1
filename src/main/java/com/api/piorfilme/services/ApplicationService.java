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

        HashMap<String, List<Long>> winnerProducersAndYears = this.producerService.getWinnerProducersAndYears();

        for (var producerAndYears: winnerProducersAndYears.entrySet()) {

            //Busca os anos dos menores e dos maiores intervalos
            maxPrevYear = producerAndYears.getValue().get(0);
            maxFollowYear = producerAndYears.getValue().get(producerAndYears.getValue().size() - 1);
            minPrevYear = producerAndYears.getValue().get(0);
            minFollowYear = producerAndYears.getValue().get(1);

            Long interval = minFollowYear - minPrevYear;
            for (int i = 1; i < producerAndYears.getValue().size() - 1; i++) {
                if ((producerAndYears.getValue().get(i + 1) - producerAndYears.getValue().get(i)) < interval) {
                    interval = producerAndYears.getValue().get(i + 1) - producerAndYears.getValue().get(i);
                    minPrevYear = producerAndYears.getValue().get(i);
                    minFollowYear = producerAndYears.getValue().get(i + 1);
                }
            }
            //na primeira iteração não há dados, portanto cria-os e segue para a próxima
            if (returnDTO.getMin().size() == 0 && returnDTO.getMax().size() == 0) {

                this.insertIntoList(returnDTO.getMin(), producerAndYears.getKey(), minPrevYear, minFollowYear);
                this.insertIntoList(returnDTO.getMax(), producerAndYears.getKey(), maxPrevYear, maxFollowYear);
                continue;
            }

            //se o intervalo registrado como minimo for maior que o encontrado
            //limpa a lista e adiciona o novo elemento. Se o intervalo for igual adiciona o item
            // à lista
            auxStoredInterval = ((PrizeIntervalDTO) returnDTO.getMin().get(0)).getInterval();
            if (auxStoredInterval >= (minFollowYear - minPrevYear)) {

                if (auxStoredInterval > (minFollowYear - minPrevYear)) {
                    returnDTO.getMin().clear();
                }
                this.insertIntoList(returnDTO.getMin(), producerAndYears.getKey(), minPrevYear, minFollowYear);

            }

            //se o intervalo registrado como maximo for menor que o encontrado
            //lima a lista e adiciona o novo elemento. Se o intervalo for igual apenas
            //adiciona o elemento à lista
            auxStoredInterval = ((PrizeIntervalDTO) returnDTO.getMax().get(0)).getInterval();
            if (auxStoredInterval <= (maxFollowYear - maxPrevYear)) {

                if (auxStoredInterval < (maxFollowYear - maxPrevYear)) {
                    returnDTO.getMax().clear();
                }

                this.insertIntoList(returnDTO.getMax(), producerAndYears.getKey(), maxPrevYear, maxFollowYear);
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
