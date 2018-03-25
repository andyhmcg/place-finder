package com.amcg.controller;



import com.amcg.model.Venue;
import com.amcg.response.PlaceFinderResponse;
import com.amcg.service.PlaceFinderService;
import com.amcg.validation.NotEmptyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
@Validated
public class PlaceController {

    @Autowired
    PlaceFinderService placeFinderService;

    @GetMapping(value = "/places", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Future<PlaceFinderResponse> getPlaces(final  @RequestParam @NotEmptyString String name){

        return CompletableFuture.supplyAsync(() -> {
            List<Venue> venues = placeFinderService.findVenuesNearNamedLocation(name);
            return new PlaceFinderResponse(venues);
        });
    }
}
