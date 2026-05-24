package com.milmile.movie.messaging;

import events.MovieAddedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MovieEventPublisher {

    @EventListener
    public void onMovieAdded(MovieAddedEvent movieAddedEvent) {
    }

}
