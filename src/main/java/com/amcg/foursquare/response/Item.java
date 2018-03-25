package com.amcg.foursquare.response;

import com.amcg.model.Venue;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Item {

    private Venue venue;
}
