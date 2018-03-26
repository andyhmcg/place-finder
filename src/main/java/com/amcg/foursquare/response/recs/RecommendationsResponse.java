package com.amcg.foursquare.response.recs;


import com.amcg.foursquare.response.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationsResponse {

    private List<Group> groups;

}
