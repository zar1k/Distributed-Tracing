package com.btc.ranking.controller;

import com.btc.ranking.dto.FavoriteDtoRequest;
import com.btc.ranking.dto.FavoriteDtoResponse;
import com.btc.ranking.service.OracleRankingService;

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
    private final OracleRankingService rankingService;

    public RankingController(OracleRankingService rankingService) {
        this.rankingService = rankingService;
    }

    @PostMapping
    public ResponseEntity<FavoriteDtoResponse> save(@RequestBody FavoriteDtoRequest favoriteDtoRequest) {
        FavoriteDtoResponse favoriteDtoResponse = this.rankingService.cache(favoriteDtoRequest);
        return ResponseEntity.ok(favoriteDtoResponse);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<FavoriteDtoResponse> find(@PathVariable int movieId) {
        FavoriteDtoResponse favoriteDtoResponse = this.rankingService.getCachedValue(movieId);
        return ResponseEntity.ok(favoriteDtoResponse);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> delete(@PathVariable int movieId) {
        this.rankingService.deleteCachedValue(movieId);
        return ResponseEntity.ok()
                             .build();
    }
}
