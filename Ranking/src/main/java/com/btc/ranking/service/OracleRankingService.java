package com.btc.ranking.service;

import com.btc.ranking.dto.FavoriteDtoRequest;
import com.btc.ranking.dto.FavoriteDtoResponse;
import com.btc.ranking.model.MovieRank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.random.RandomGenerator;

@Service
public class OracleRankingService {
    private final Logger logger = LoggerFactory.getLogger(OracleRankingService.class);
    private final ValueOperations<Integer, Object> valueOps;

    public OracleRankingService(final RedisTemplate<Integer, Object> redisTemplate) {
        this.valueOps = redisTemplate.opsForValue();
    }

    public FavoriteDtoResponse cache(FavoriteDtoRequest favoriteDtoRequest) {
        int rank = RandomGenerator.getDefault()
                                  .nextInt(1, (int) favoriteDtoRequest.getRating());
        final MovieRank movieRank = new MovieRank(favoriteDtoRequest.getMovieId(), rank);
        logger.info("Add movie rank to cache:{}", movieRank);
        valueOps.set(movieRank.getMovieId(), movieRank);
        return buildFavoriteDtoResponse(movieRank);
    }

    public FavoriteDtoResponse getCachedValue(int movieId) {
        logger.info("Get movie rank from cache by movieId:{}", movieId);
        MovieRank movieRank = (MovieRank) valueOps.get(movieId);
        return buildFavoriteDtoResponse(movieRank);
    }

    public void deleteCachedValue(int movieId) {
        logger.info("Delete movie rank from cache by movieId:{}", movieId);
        valueOps.getOperations()
                .delete(movieId);
    }

    private FavoriteDtoResponse buildFavoriteDtoResponse(MovieRank movieRank) {
        return new FavoriteDtoResponse(movieRank.getMovieId(), movieRank.getRank());
    }
}
