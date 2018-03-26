package com.amcg.foursquare.client;


import com.amcg.foursquare.response.Group;
import com.amcg.foursquare.response.Item;
import com.amcg.foursquare.response.pop.FourSquarePopularResponse;
import com.amcg.foursquare.response.recs.FourSquareRecommendationsResponse;
import com.amcg.foursquare.response.search.FourSquareSearchResponse;
import com.amcg.model.Location;
import com.amcg.model.Venue;
import org.springframework.beans.factory.annotation.Value;
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


    private static final String LL_PARAMETER = "%s,%s";
    @Value("${four.square.search.url}")
    private String searchUrl;

    @Value("${four.square.explore.url}")
    private String exploreUrl;

    @Value("${four.square.trending.url}")
    private String trendingUrl;

    public FourSquareClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }


    public List<Venue> findVenueByName(Location location, String name){

        String ll = String.format(LL_PARAMETER,  location.getLng(), location.getLat());
        FourSquareSearchResponse result = restTemplate.getForObject(searchUrl, FourSquareSearchResponse.class, name, ll);

        return result.getResponse().getVenues();
    }


    public  List<Venue>  findRecommendedVenues(Location location){

        String ll = String.format(LL_PARAMETER,  location.getLng(), location.getLat());
        FourSquareRecommendationsResponse result = restTemplate.getForObject(exploreUrl, FourSquareRecommendationsResponse.class, ll);

        return result.getResponse().getGroups()
                .stream()
                .flatMap((Function<Group, Stream<Venue>>) (Group group) -> group.getItems()
                        .stream().map(Item::getVenue)
                )
                .collect(Collectors.toList());

    }


    public List<Venue> findPopularVenues(Location location){

        String ll = String.format(LL_PARAMETER,  location.getLng(), location.getLat());
        FourSquarePopularResponse result = restTemplate.getForObject(trendingUrl, FourSquarePopularResponse.class, ll);

        return result.getResponse().getVenues();
    }
}
