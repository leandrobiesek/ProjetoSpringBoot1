package com.api.piorfilme.services;
import com.api.piorfilme.models.ProducerModel;
import com.api.piorfilme.models.ProducerMovieModel;
import com.api.piorfilme.repositories.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerService extends GenericCrudServiceImpl<ProducerModel,Long> {

    final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    protected JpaRepository<ProducerModel, Long> getRepository() {
        return producerRepository;
    }



    /**
     * Processa a lista de produtores vencedores e retorna um hashmap
     * contando apenas os produtores que venceram pelo menos 2 premios.
     *
     * @return      Hashmap com a chave sendo o nome do produtor e os valores
     *  sendo uma lista contendo os anos em que o produtor venceu o prêmio.
     */
    public HashMap<String, List<Long>> getWinnerProducersAndYears() {
        List<Long> winnerYears;
        HashMap<String, List<Long>> retmap = new HashMap<>();

        //Carrega a lista de produtores
        List<ProducerModel> producerModels = this.producerRepository.findAllWinnerProducersAndYears();
        for (ProducerModel prod: producerModels) {
            winnerYears = prod.getProducerMovieModelList().stream()
                    .filter(el -> el.getMovie().getWinner())
                    .map(el -> el.getMovie().getAno())
                    .collect(Collectors.toList());

            if (winnerYears.size() > 1) {
                retmap.put(prod.getName(), winnerYears);
            }
        }

        return retmap;
    }
}
