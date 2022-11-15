package com.api.piorfilme.services;

import com.api.piorfilme.models.*;
import com.api.piorfilme.repositories.MovieRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {

    private final MovieService movieService;

    public CSVService(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Processa a string contendo os produtores do filme, separando-os por registro.
     * Em seguida verifica se o registro contendo o nome do produtor já está na lista
     * passada como parametro, inserindo registros não existentes e retornando uma lista
     * que referencia todos os elementos
     *
     * @param  producerModelList Lista de produtores existentes
     * @param  producersRecord String contendo o nome dos produtores
     * @return      Lista de producerModel contendo os produtores passados como parametro
     */
    private List<ProducerModel> processRecordProducers(List<ProducerModel> producerModelList, String producersRecord) {
        List<ProducerModel> retList = new ArrayList<>();
        ProducerModel producerAux;
        for (String producerName : producersRecord.split("(, and |, | and )")) {

            producerAux = producerModelList.stream().filter(el -> el.getName().equals(producerName.trim())).findFirst().orElse(null);
            if (producerAux == null) {
                producerAux = new ProducerModel(producerName.trim());
                producerModelList.add(producerAux);
            }

            retList.add(producerAux);
        }
        return retList;
    }

    /**
     * Processa a string contendo os estúdios do filme, separando-os por registro.
     * Em seguida verifica se o registro contendo o nome do estúdio já está na lista
     * passada como parametro, inserindo registros não existentes e retornando uma lista
     * que referencia todos os elementos
     *
     * @param  studioModelList Lista de estudios existentes
     * @param  studiosRecord String contendo o nome dos estúdios
     * @return      Lista de studiosModel contendo os produtores passados como parametro
     *
     */
    private List<StudioModel> processRecordStudios(List<StudioModel> studioModelList, String studiosRecord) {
        List<StudioModel> retList = new ArrayList<>();
        StudioModel studioAux;
        for (String studioName : studiosRecord.split("(,)")) {
            studioAux = studioModelList.stream().filter(el -> el.getName().equals(studioName.trim())).findFirst().orElse(null);
            if (studioAux == null) {
                studioAux = new StudioModel(studioName.trim());
                studioModelList.add(studioAux);
            }

            retList.add(studioAux);
        }
        return retList;
    }

    /**
     * Carrega os dados do CSV contido na pasta resources/static e os converte
     * para uma lista de movieModel
     *
     * @param  csvName nome do arquivo CSV contido na pasta resources/static
     * @return      Lista de movieModel lidos do CSV passado por parametro
     */

    public List<MovieModel> loadResourceCSV(String csvName) {

        try {
            File file = ResourceUtils.getFile("classpath:"+csvName);

            try (BufferedReader fileReader = new BufferedReader(new FileReader(file));

                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT
                                 .withFirstRecordAsHeader()
                                 .withIgnoreHeaderCase()
                                 .withDelimiter(';')
                                 .withTrim());
                 ) {

                List<CSVRecord> csvRecords = csvParser.getRecords();
                List<MovieModel> movieModelList = new ArrayList<>();
                List<ProducerModel> producerModelListRead = new ArrayList<>();
                List<ProducerMovieModel> producerMovieModelList = new ArrayList<>();
                List<StudioModel> studioModelListRead = new ArrayList<>();
                List<StudioMovieModel> studioMovieModelList = new ArrayList<>();
                MovieModel movie;
                for (CSVRecord csvRecord : csvRecords) {
                    movie = new MovieModel(Long.parseLong(csvRecord.get(0)), csvRecord.get(1), csvRecord.get(4));

                    //cria as relações entre o filme e os produtores
                    for (ProducerModel producer : this.processRecordProducers(producerModelListRead, csvRecord.get(3))) {
                        movie.getProducerMovieModelList().add(new ProducerMovieModel(movie, producer));
                    }

                    //cria as relações entre o filme e os estúdios
                    for (StudioModel studio : this.processRecordStudios(studioModelListRead, csvRecord.get(2))) {
                        movie.getStudioMovieModelList().add(new StudioMovieModel(movie, studio));
                    }
                    movieModelList.add(movie);
                }

                return movieModelList;

            } catch (IOException e) {
                throw new RuntimeException("Falha ao ler o arquivo CSV: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo CSV não encontrado: " + e.getMessage());
        }
    }

    @Transactional
    public void saveResourceCSV(String csvName) {
        this.movieService.save(this.loadResourceCSV(csvName));
    }
}
