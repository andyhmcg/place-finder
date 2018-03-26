package com.amcg.service;

import com.amcg.foursquare.client.FourSquareClient;
import com.amcg.foursquare.response.Group;
import com.amcg.foursquare.response.Item;
import com.amcg.foursquare.response.Meta;
import com.amcg.foursquare.response.pop.FourSquarePopularResponse;
import com.amcg.foursquare.response.pop.PopularResponse;
import com.amcg.foursquare.response.recs.FourSquareRecommendationsResponse;
import com.amcg.foursquare.response.recs.RecommendationsResponse;
import com.amcg.foursquare.response.search.FourSquareSearchResponse;
import com.amcg.foursquare.response.search.SearchResponse;
import com.amcg.model.Location;
import com.amcg.model.Venue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FourSquareClientTest {

    private static final Location location = Location.builder().lat(1.0).lng(1.0).build();
    public static final String COSTA_COFFEE = "Costa";

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    private FourSquareClient fourSquareClient;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){

        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        fourSquareClient = new FourSquareClient(restTemplateBuilder);

        ReflectionTestUtils.setField(fourSquareClient,"searchUrl","search.url");
        ReflectionTestUtils.setField(fourSquareClient,"exploreUrl","explore.url");
        ReflectionTestUtils.setField(fourSquareClient,"trendingUrl","trending.url");

    }

    @Test
    public void findVenueByName() {

        Location l1 = Location.builder().lng(1.0).lat(1.0).build();
        Venue venue = Venue.builder().id("ID_1").name("VENUE_1").Location(l1).build();
        Meta meta = Meta.builder().code(200).build();
        SearchResponse searchResponse = SearchResponse.builder().venues(Collections.singletonList(venue)).build();
        FourSquareSearchResponse response = FourSquareSearchResponse.builder().meta(meta).response(searchResponse).build();

        when(restTemplate.getForObject(any(),any(),anyString(),anyString())).thenReturn(response);

        List<Venue> result = fourSquareClient.findVenueByName(location, COSTA_COFFEE);

        assertThat("Venues", result, hasSize(1));

        verify(restTemplate).getForObject(any(),any(),anyString(),anyString());
    }

    @Test
    public void findRecomendedVenues() {

        Location l1 = Location.builder().lng(1.0).lat(1.0).build();
        Venue venue = Venue.builder().id("ID_1").name("VENUE_1").Location(l1).build();
        Meta meta = Meta.builder().code(200).build();

        Item item = Item.builder().venue(venue).build();
        Group group = Group.builder().items(Collections.singletonList(item)).build();

        RecommendationsResponse searchResponse = RecommendationsResponse.builder().groups(Collections.singletonList(group)).build();
        FourSquareRecommendationsResponse response = FourSquareRecommendationsResponse.builder().meta(meta).response(searchResponse).build();

        when(restTemplate.getForObject(any(),any(),anyString())).thenReturn(response);

        List<Venue> result = fourSquareClient.findRecommendedVenues(l1);

        assertThat("Venues", result, hasSize(1));
        assertThat("Recommended Venue", result, contains(venue));

        verify(restTemplate).getForObject(any(),any(),anyString());
    }

    @Test
    public void findPopularVenues(){

        Location l1 = Location.builder().lng(1.0).lat(1.0).build();

        Venue venue = Venue.builder().id("ID_1").name("VENUE_1").Location(l1).build();

        Meta meta = Meta.builder().code(200).build();
        PopularResponse popularResponse = PopularResponse.builder().venues(Collections.singletonList(venue)).build();
        FourSquarePopularResponse response = FourSquarePopularResponse.builder().meta(meta).response(popularResponse).build();

        when(restTemplate.getForObject(any(),any(),anyString())).thenReturn(response);

        List<Venue> result = fourSquareClient.findPopularVenues(l1);

        assertThat("Venues", result, hasSize(1));
        assertThat("Popular Venue", result, contains(venue));

        verify(restTemplate).getForObject(any(),any(),anyString());


    }

    @Test
    public void findPopularVenuesCanThrowRestClientException(){


        Location l1 = Location.builder().lng(1.0).lat(1.0).build();
        when(restTemplate.getForObject(any(),any(),anyString())).thenThrow(new RestClientException("RestClientException"));

        expectedException.expect(RestClientException.class);
        try {
            fourSquareClient.findPopularVenues(l1);
        }
        finally {
            verify(restTemplate).getForObject(any(),any(),anyString());
        }
    }

    @Test
    public void findRecomendedVenuesCanThrowRestClientException() {

        Location l1 = Location.builder().lng(1.0).lat(1.0).build();

        expectedException.expect(RestClientException.class);
        when(restTemplate.getForObject(any(),any(),anyString())).thenThrow(new RestClientException("RestClientException"));

        try {
            fourSquareClient.findRecommendedVenues(l1);
        }
        finally {
            verify(restTemplate).getForObject(any(),any(),anyString());
        }

    }

    @Test
    public void findVenueByNameCanThrowRestClientException() {

        expectedException.expect(RestClientException.class);
        when(restTemplate.getForObject(any(),any(),anyString(),anyString())).thenThrow(new RestClientException("RestClientException"));

        try {
            fourSquareClient.findVenueByName(location, COSTA_COFFEE);
        }
        finally {
            verify(restTemplate).getForObject(any(),any(),anyString(),anyString());
        }


    }
}