package com.milmile.recommendation.service;

import events.MovieAddedEvent;
import com.milmile.recommendation.dto.RecommendationEvents;
import com.milmile.recommendation.mapper.RecommendationMapper;
import com.milmile.recommendation.repository.MovieRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ApplicationEventPublisher eventPublisher;

    public MovieService(MovieRepository movieRepository, ApplicationEventPublisher eventPublisher) {
        this.movieRepository = movieRepository;
        this.eventPublisher = eventPublisher;
    }

    public void addMovie(MovieAddedEvent movieAddedEvent){
        var entity = RecommendationMapper.toMovie(movieAddedEvent);
        this.movieRepository.save(entity);
        this.eventPublisher.publishEvent(new RecommendationEvents.NewMovieEvent(movieAddedEvent.movieId()));
    }

}
