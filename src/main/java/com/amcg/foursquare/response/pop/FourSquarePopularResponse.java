package com.amcg.foursquare.response.pop;

import com.amcg.foursquare.response.Meta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FourSquarePopularResponse {

    private Meta meta;
    private PopularResponse response;

}
