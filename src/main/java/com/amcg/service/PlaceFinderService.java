package com.amcg.service;

import com.amcg.model.Location;
import com.amcg.model.Venue;

import java.util.List;

public interface PlaceFinderService {

    List<Venue> findVenuesNearNamedLocation(Location location, String name);
}
