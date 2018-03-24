package com.amcg.controller;


import com.amcg.model.Venue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

@RestController
public class PlaceController {

    @GetMapping("/places")
    public Future<List<Venue>> getPlaces(final @Valid @NotBlank @RequestParam String name,
                                        @Valid @RequestParam(defaultValue = "true") Boolean recsOrPopular){




        return CompletableFuture.supplyAsync(() -> Collections.emptyList());
    }
}
