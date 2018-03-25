package com.amcg.foursquare.response.pop;

import com.amcg.model.Venue;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PopularResponse {

    private List<Venue> venues;
}
