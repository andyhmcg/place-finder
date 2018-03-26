package com.amcg.foursquare.response.pop;

import com.amcg.model.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopularResponse {

    private List<Venue> venues;
}
