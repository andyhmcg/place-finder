package com.amcg.service;

import com.amcg.model.Venue;

import java.util.List;

public interface PlaceFinderService {

    List<Venue> findPlaceByName(String name);
    List<Venue> findPopularPlaces(String name);
    List<Venue> findRecomendedPlaces(String name);
}
