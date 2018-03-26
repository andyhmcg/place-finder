package com.amcg.foursquare.response.recs;


import com.amcg.foursquare.response.Meta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FourSquareRecommendationsResponse {

    private Meta meta;
    private RecommendationsResponse response;
}
