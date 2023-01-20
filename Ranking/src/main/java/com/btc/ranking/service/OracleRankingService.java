package com.btc.ranking.service;

import com.btc.ranking.dto.FavoriteDtoRequest;
import com.btc.ranking.dto.FavoriteDtoResponse;
import com.btc.ranking.model.MovieRank;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.random.RandomGenerator;

@Service
public class OracleRankingService {
    private final ValueOperations<Integer, Object> valueOps;

    public OracleRankingService(final RedisTemplate<Integer, Object> redisTemplate) {
        this.valueOps = redisTemplate.opsForValue();
    }

    public FavoriteDtoResponse cache(FavoriteDtoRequest favoriteDtoRequest) {
        int rank = RandomGenerator.getDefault()
                                  .nextInt(1, (int) favoriteDtoRequest.getRating());
        final MovieRank movieRank = new MovieRank(favoriteDtoRequest.getMovieId(), rank);
        valueOps.set(movieRank.getMovieId(), movieRank);
        return buildFavoriteDtoResponse(movieRank);
    }

    public FavoriteDtoResponse getCachedValue(int movieId) {
        MovieRank movieRank = (MovieRank) valueOps.get(movieId);
        return buildFavoriteDtoResponse(movieRank);
    }

    public void deleteCachedValue(int movieId) {
        valueOps.getOperations()
                .delete(movieId);
    }

    private FavoriteDtoResponse buildFavoriteDtoResponse(MovieRank movieRank) {
        return new FavoriteDtoResponse(movieRank.getMovieId(), movieRank.getRank());
    }
}
