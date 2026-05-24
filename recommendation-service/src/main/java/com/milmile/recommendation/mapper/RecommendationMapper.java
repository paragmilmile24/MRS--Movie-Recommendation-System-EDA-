package com.milmile.recommendation.mapper;

import events.CustomerGenreUpdatedEvent;
import events.MovieAddedEvent;
import com.milmile.recommendation.dto.MovieSummary;
import com.milmile.recommendation.entity.CustomerGenre;
import com.milmile.recommendation.entity.Movie;

public class RecommendationMapper {

    public static CustomerGenre toCustomerGenre(CustomerGenreUpdatedEvent genreUpdatedEvent){
        var entity = new CustomerGenre();
        entity.setCustomerId(genreUpdatedEvent.customerId());
        entity.setFavoriteGenre(genreUpdatedEvent.favoriteGenre());
        return entity;
    }

    public static Movie toMovie(MovieAddedEvent movieAddedEvent){
        var entity = new Movie();
        entity.setId(movieAddedEvent.movieId());
        entity.setTitle(movieAddedEvent.title());
        entity.setVoteCount(movieAddedEvent.voteCount());
        entity.setReleaseDate(movieAddedEvent.releaseDate());
        entity.setRuntime(movieAddedEvent.runtime());
        entity.setPosterPath(movieAddedEvent.posterPath());
        entity.setGenres(movieAddedEvent.genres());
        return entity;
    }

    public static MovieSummary toMovieSummary(Movie movie){
        return new MovieSummary(
                movie.getId(),
                movie.getTitle(),
                movie.getRuntime(),
                movie.getVoteCount(),
                movie.getReleaseDate(),
                movie.getGenres(),
                movie.getPosterPath()
        );
    }

}
