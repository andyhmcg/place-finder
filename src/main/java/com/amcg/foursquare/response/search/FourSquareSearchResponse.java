package com.amcg.foursquare.response.search;

import com.amcg.foursquare.response.Meta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FourSquareSearchResponse {

    private Meta meta;
    private SearchResponse response;

}


