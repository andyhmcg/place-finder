package com.amcg.foursquare.response.search;

import com.amcg.foursquare.response.Meta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FourSquareSearchResponse {

    private Meta meta;
    private SearchResponse response;

}


