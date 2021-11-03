package com.marcuschiu.example.graphqldgs.datafetcher;

import com.marcuschiu.example.graphqldgs.model.Director;
import com.marcuschiu.example.graphqldgs.model.Review;
import com.marcuschiu.example.graphqldgs.model.Show;
import com.netflix.graphql.dgs.*;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@DgsComponent
public class ShowsDatafetcher {

    private final List<Show> shows = List.of(
            new Show("Stranger Things", 2016, "Marcus"),
            new Show("Ozark", 2017, "Erina"),
            new Show("The Crown", 2016, "Anna"),
            new Show("Dead to Me", 2019, "Eric"),
            new Show("Orange is the New Black", 2013, "Marcus")
    );

    @DgsData(parentType = "Query", field = "shows")
    public List<Show> shows(@InputArgument String titleFilter) {
        if(titleFilter == null) {
            return shows;
        }

        return shows.stream().filter(s -> s.getTitle().contains(titleFilter)).collect(Collectors.toList());
    }

    @DgsData(parentType = "Show", field = "reviews")
    public List<Review> reviews(DgsDataFetchingEnvironment dfe) {
        Show show = dfe.getSource();
        return List.of(Review.builder().description(show.getTitle()).build());
    }

    @DgsData(parentType = "Review", field = "show")
    public Show show(DgsDataFetchingEnvironment dfe) {
        Review review = dfe.getSource();
        return shows.stream().filter(s -> s.getTitle().contains(review.getDescription())).findFirst().get();
    }

    @DgsData(parentType = "Show", field = "director")
    public CompletableFuture<Director> director(DataFetchingEnvironment dfe) {
        DataLoader<String, Director> dataLoader = dfe.getDataLoader("directors");
        Show show = dfe.getSource();
        String id = show.getDirectorName();
        return dataLoader.load(id);
    }

    @DgsData(parentType = "Director", field = "show")
    public Show showDirector(DgsDataFetchingEnvironment dfe) {
        Director director = dfe.getSource();
        return shows.get(0);
    }
}