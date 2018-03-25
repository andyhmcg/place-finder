package com.amcg.service;

import com.amcg.foursquare.client.FourSquareClient;
import com.amcg.model.Location;
import com.amcg.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public List<Venue> findVenuesNearNamedLocation(String name) {

        List<Venue> venuesByName =  fourSquareClient.findVenueByName(name);

        return venuesByName.stream().map(Venue::getLocation)
                 .flatMap((Function<Location, Stream<Venue>>) location -> {

                    List<Venue> popular = fourSquareClient.findPopularVenues(location);
                    List<Venue> recommended = fourSquareClient.findRecommendedVenues(location);

                    return Stream.concat(popular.stream(), recommended.stream());
                })
                .distinct()
                .collect(Collectors.toList());

    }

}
