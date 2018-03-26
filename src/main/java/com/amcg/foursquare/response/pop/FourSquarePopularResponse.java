package com.amcg.foursquare.response.pop;

import com.amcg.foursquare.response.Meta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FourSquarePopularResponse {

    private Meta meta;
    private PopularResponse response;

}
