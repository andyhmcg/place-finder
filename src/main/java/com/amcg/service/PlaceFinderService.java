package com.amcg.service;

import com.amcg.model.Venue;

import java.util.List;

public interface PlaceFinderService {

    List<Venue> findVenuesNearNamedLocation(String name);
}
