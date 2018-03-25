package com.amcg.foursquare.response.recs;


import com.amcg.foursquare.response.Meta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FourSquareRecommendationsResponse {

    private Meta meta;
    private RecommendationsResponse response;
}
