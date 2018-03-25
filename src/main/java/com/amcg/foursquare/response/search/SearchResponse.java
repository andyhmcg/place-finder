package com.amcg.foursquare.response.search;

import com.amcg.model.Venue;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResponse {

    private List<Venue> venues;
}
