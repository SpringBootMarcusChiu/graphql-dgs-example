package com.marcuschiu.example.graphqldgs.dataloaders;

import com.marcuschiu.example.graphqldgs.model.Director;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@DgsDataLoader(name = "directors", maxBatchSize = 2)
public class DirectorsDataLoader implements BatchLoader<String, Director> {

    private final List<Director> directors = List.of(
            Director.builder().name("Marcus").build(),
            Director.builder().name("Erina").build(),
            Director.builder().name("Anna").build(),
            Director.builder().name("Eric").build()
    );

    @Override
    public CompletionStage<List<Director>> load(List<String> keys) {
        return CompletableFuture.supplyAsync(() -> directors.stream().filter(d -> keys.contains(d.getName())).collect(Collectors.toList()));
    }
}
