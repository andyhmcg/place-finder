package com.amcg.service;

import com.amcg.foursquare.client.FourSquareClient;
import com.amcg.model.Location;
import com.amcg.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PlaceFinderServiceImpl implements PlaceFinderService {

    final private FourSquareClient fourSquareClient;

    @Autowired
    public PlaceFinderServiceImpl(FourSquareClient fourSquareClient) {
        this.fourSquareClient = fourSquareClient;
    }

    /**
     * For each Venue found by name find other Venues which have been Recommended or are Popular
     * @param location Current Location
     * @param name of Venue
     * @return List of popular or recommended Venues
     */
    @Override
    public List<Venue> findVenuesNearNamedLocation(Location location,String name) {

        List<Venue> venuesByName =  fourSquareClient.findVenueByName(location, name);

        return venuesByName.parallelStream().map(Venue::getLocation)
                 .flatMap((Function<Location, Stream<Venue>>) l -> {

                     CompletableFuture<List<Venue>> cfPopular = CompletableFuture.supplyAsync(() -> fourSquareClient.findPopularVenues(l));
                     CompletableFuture<List<Venue>> cfRecommended = CompletableFuture.supplyAsync(() -> fourSquareClient.findRecommendedVenues(l));
                     CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(cfPopular,cfRecommended);

                     try {
                         combinedFuture.get();
                     } catch (Exception e) {
                         throw new RuntimeException("Error getting Venues");
                     }

                     return Stream.of(cfPopular, cfRecommended)
                             .flatMap((Function<CompletableFuture<List<Venue>>, Stream<Venue>>) cf -> cf.join().stream());

                })
                .distinct()
                .collect(Collectors.toList());

    }

}
