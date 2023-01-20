package com.btc.moviechart.service;

import com.btc.moviechart.dto.FavoriteDtoRequest;
import com.btc.moviechart.dto.FavoriteDtoResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RankingService {
    private final Logger logger = LoggerFactory.getLogger(RankingService.class);
    private final String rankingUrl = "http://localhost:8089/api/v1/ranking";
    private final RestTemplate restTemplate;

    public RankingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FavoriteDtoResponse save(Integer id, double rating) {
        FavoriteDtoRequest body = new FavoriteDtoRequest();
        body.setMovieId(id);
        body.setRating(rating);
        logger.info("Add rank to movie:{}", body);
        ResponseEntity<FavoriteDtoResponse> response = restTemplate.postForEntity(
                rankingUrl,
                body,
                FavoriteDtoResponse.class);
        return response.getBody();
    }

    public FavoriteDtoResponse findById(int movieId) {
        logger.info("Find rank by movieId:{}", movieId);
        ResponseEntity<FavoriteDtoResponse> response = restTemplate.getForEntity(
                rankingUrl + "/" + movieId,
                FavoriteDtoResponse.class);
        return response.getBody();
    }

    public void deleteById(int movieId) {
        logger.info("Delete rank by movieId:{}", movieId);
        Map<String, Integer> params = new HashMap<>();
        params.put("movieId", movieId);
        restTemplate.delete(rankingUrl + "/{movieId}", params);
    }
}
