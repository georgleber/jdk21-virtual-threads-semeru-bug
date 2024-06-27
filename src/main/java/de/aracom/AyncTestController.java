package de.aracom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/async-test")
public class AyncTestController {

    private final AsyncService asyncService;

    public AyncTestController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping(value = "/{amount}", produces = "application/json;charset=UTF-8")
    public List<String> testAsync(@PathVariable int amount) {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            futures.add(this.asyncService.execute());
        }

        // Alle Tarife ausführen und die Ergebnisse sammeln
        return CompletableFuture
            .allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .toList()
            )
            .join();
    }
}
