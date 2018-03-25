package com.amcg.foursquare.response.recs;


import com.amcg.foursquare.response.Group;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecommendationsResponse {

    private List<Group> groups;

}
