package com.btc.ranking.controller;

import com.btc.ranking.dto.FavoriteDtoRequest;
import com.btc.ranking.dto.FavoriteDtoResponse;
import com.btc.ranking.service.OracleRankingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ranking")
public class RankingController {
    private final Logger logger = LoggerFactory.getLogger(RankingController.class);
    private final OracleRankingService rankingService;

    public RankingController(OracleRankingService rankingService) {
        this.rankingService = rankingService;
    }

    @PostMapping
    public ResponseEntity<FavoriteDtoResponse> save(@RequestBody FavoriteDtoRequest favoriteDtoRequest) {
        logger.info("Save movie rank:{}", favoriteDtoRequest);
        FavoriteDtoResponse favoriteDtoResponse = this.rankingService.cache(favoriteDtoRequest);
        return ResponseEntity.ok(favoriteDtoResponse);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<FavoriteDtoResponse> find(@PathVariable int movieId) {
        logger.info("Find movie rank by movieId:{}", movieId);
        FavoriteDtoResponse favoriteDtoResponse = this.rankingService.getCachedValue(movieId);
        return ResponseEntity.ok(favoriteDtoResponse);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> delete(@PathVariable int movieId) {
        logger.info("Delete movie rank by movieId:{}", movieId);
        this.rankingService.deleteCachedValue(movieId);
        return ResponseEntity.ok()
                             .build();
    }
}
