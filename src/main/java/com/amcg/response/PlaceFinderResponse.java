package com.amcg.response;

import com.amcg.model.Venue;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaceFinderResponse {

    List<Venue> venues;
}
