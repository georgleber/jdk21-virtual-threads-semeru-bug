package de.aracom;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    private static final List<String> CHARACTER_NAMES = List.of(
        "Luke Skywalker",
        "R2-D2",
        "C-3PO",
        "Darth Vader",
        "Leia Organa",
        "Owen Lars",
        "R5-D4",
        "Biggs Darklighter",
        "Obi-Wan Kenobi",
        "Chewbacca",
        "Han Solo",
        "Wedge Antilles"
    );

    @Async
    public CompletableFuture<String> execute() {
        Random rand = new Random();
        int randomNum = rand.nextInt(12);

        try {
            int randomSleep = rand.nextInt(5000 - 500) + 500;

            Thread.sleep(randomSleep);
            return CompletableFuture.completedFuture(CHARACTER_NAMES.get(rand.nextInt(12)));
        } catch (InterruptedException e) {
            //
        }

        return null;
    }
}
