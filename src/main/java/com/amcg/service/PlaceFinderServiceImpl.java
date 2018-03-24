package com.amcg.service;

import com.amcg.model.Venue;

import java.util.Collections;
import java.util.List;

public class PlaceFinderServiceImpl implements PlaceFinderService {
    @Override
    public List<Venue> findPlaceByName(String name) {
        return Collections.emptyList();
    }

    @Override
    public List<Venue> findPopularPlaces(String name) {
        return Collections.emptyList();
    }

    @Override
    public List<Venue> findRecomendedPlaces(String name) {
        return Collections.emptyList();
    }
}
