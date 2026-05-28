package com.milmile.recommendation.service;
import com.milmile.recommendation.dto.MovieSummary;
import com.milmile.recommendation.mapper.RecommendationMapper;
import com.milmile.recommendation.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecommendationService {

    private final MovieRepository movieRepository;

    public RecommendationService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieSummary> findNewlyAdded() {
        return this.movieRepository.findTop10ByOrderByCreatedAtDesc()
                .stream()
                .map(RecommendationMapper::toMovieSummary)
                .toList();
    }

    public List<MovieSummary> findPersonalized(Integer customerId) {
        return this.movieRepository.findPersonalized(customerId)
                .stream()
                .map(RecommendationMapper::toMovieSummary)
                .toList();
    }

    public MovieSummary findMovie(Integer movieId) {
        return this.movieRepository.findById(movieId)
                .map(RecommendationMapper::toMovieSummary)
                .orElseThrow();
    }

}
