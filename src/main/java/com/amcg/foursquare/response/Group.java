package com.amcg.foursquare.response;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Group {

    private List<Item> items;
}
