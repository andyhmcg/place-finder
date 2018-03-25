package com.amcg.service;

import com.amcg.model.Venue;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PlaceFinderServiceImpl implements PlaceFinderService {
    public List<Venue> findPlaceByName(String name) {
        return Collections.emptyList();
    }


    public List<Venue> findPopularPlaces(String name) {
        return Collections.emptyList();
    }


    public List<Venue> findRecomendedPlaces(String name) {
        return Collections.emptyList();
    }

    @Override
    public List<Venue> findVenuesNearNamedLocation(String name) {
        return Collections.emptyList();
    }

}
