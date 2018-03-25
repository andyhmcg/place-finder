package com.amcg.foursquare.client;


import com.amcg.foursquare.response.Group;
import com.amcg.foursquare.response.Item;
import com.amcg.foursquare.response.pop.FourSquarePopularResponse;
import com.amcg.foursquare.response.recs.FourSquareRecommendationsResponse;
import com.amcg.foursquare.response.search.FourSquareSearchResponse;
import com.amcg.model.Location;
import com.amcg.model.Venue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FourSquareClient {

    private final RestTemplate restTemplate;


    public FourSquareClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }


    public List<Venue> findVenueByName(String name){

        FourSquareSearchResponse result = restTemplate.getForObject("https://api.foursquare.com/v2/venues/search?ll=51.4826,0.0077&oauth_token=QIAQK4OOJQTJU5NHXDAQE4QAVGMUXLMFEZPSO3MJD4ME1B2H&v=20180324", FourSquareSearchResponse.class, name);


        return result.getResponse().getVenues();
    }


    public  List<Venue>  findRecommendedVenues(Location location){

        FourSquareRecommendationsResponse result = restTemplate.getForObject("https://api.foursquare.com/v2/venues/explore?ll=51.4826,0.0077&oauth_token=QIAQK4OOJQTJU5NHXDAQE4QAVGMUXLMFEZPSO3MJD4ME1B2H&v=20180324", FourSquareRecommendationsResponse.class);

        return result.getResponse().getGroups()
                .stream()
                .flatMap((Function<Group, Stream<Venue>>) (Group group) -> group.getItems()
                        .stream().map(Item::getVenue)
                )
                .collect(Collectors.toList());

    }

    public List<Venue> findPopularVenues(Location location){

        FourSquarePopularResponse result = restTemplate.getForObject("https://api.foursquare.com/v2/venues/trending?ll=51.4826,0.0077&oauth_token=QIAQK4OOJQTJU5NHXDAQE4QAVGMUXLMFEZPSO3MJD4ME1B2H&v=20180324", FourSquarePopularResponse.class);

        return result.getResponse().getVenues();
    }
}
