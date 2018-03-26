package com.amcg.service;

import com.amcg.foursquare.client.FourSquareClient;
import com.amcg.model.Location;
import com.amcg.model.Venue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PlaceFinderServiceImplTest {


    private static final Location location = Location.builder().lng(1.0).lat(1.0).build();
    private static final String COSTA_COFFEE = "COSTA";

    @Mock
    private FourSquareClient fourSquareClient;

    @InjectMocks
    private PlaceFinderServiceImpl placeFinderService;

    @Test
    public void findVenuesNearNamedLocation() {

        Venue venueA = createVenue("ID_1","VENUE_1", 1.0,1.0);
        Venue venueB = createVenue("ID_2","VENUE_2", 2.0,2.0);

        Location l1 = Location.builder().lng(1.0).lat(1.0).build();
        Location l2 = Location.builder().lng(2.0).lat(2.0).build();

        when(fourSquareClient.findVenueByName(location,COSTA_COFFEE)).thenReturn(Arrays.asList(venueA, venueB));
        when(fourSquareClient.findPopularVenues(l1)).thenReturn(createRecommendedVenues());
        when(fourSquareClient.findRecommendedVenues(l1)).thenReturn(createPopularVenues());
        when(fourSquareClient.findPopularVenues(l2)).thenReturn(Collections.emptyList());
        when(fourSquareClient.findRecommendedVenues(l2)).thenReturn(Collections.emptyList());

        List<Venue> result = placeFinderService.findVenuesNearNamedLocation(location, COSTA_COFFEE);

        assertThat("Venues not null", result, notNullValue());
        assertThat("Venues size 6", result, hasSize(6));

        verify(fourSquareClient).findVenueByName(location, COSTA_COFFEE);
        verify(fourSquareClient).findPopularVenues(l1);
        verify(fourSquareClient).findRecommendedVenues(l1);
        verify(fourSquareClient).findPopularVenues(l2);
        verify(fourSquareClient).findRecommendedVenues(l2);

    }

    @Test
    public void canHandleNoVenuesFoundByName() {


        Location l1 = Location.builder().lng(1.0).lat(1.0).build();
        when(fourSquareClient.findVenueByName(location,COSTA_COFFEE)).thenReturn(Collections.emptyList());


        List<Venue> result = placeFinderService.findVenuesNearNamedLocation(location, COSTA_COFFEE);

        assertThat("Venues not null", result, notNullValue());
        assertThat("Venues size 0", result, hasSize(0));


        verify(fourSquareClient).findVenueByName(location, COSTA_COFFEE);
        verify(fourSquareClient,never()).findPopularVenues(l1);
        verify(fourSquareClient, never()).findRecommendedVenues(l1);

    }

    @Test
    public void findVenuesNearNamedLocationmultipleLocationShouldNotReturnDuplicates() {

        Venue venueA = createVenue("ID_1","VENUE_1", 1.0,1.0);
        Venue venueB = createVenue("ID_2","VENUE_2", 2.0,2.0);

        Location l1 = Location.builder().lng(1.0).lat(1.0).build();
        Location l2 = Location.builder().lng(2.0).lat(2.0).build();


        when(fourSquareClient.findVenueByName(location,COSTA_COFFEE)).thenReturn(Arrays.asList(venueA, venueB));

        when(fourSquareClient.findPopularVenues(l1)).thenReturn(createRecommendedVenues());
        when(fourSquareClient.findRecommendedVenues(l1)).thenReturn(createPopularVenues());

        when(fourSquareClient.findPopularVenues(l2)).thenReturn(createRecommendedVenues());
        when(fourSquareClient.findRecommendedVenues(l2)).thenReturn(createPopularVenues());


        List<Venue> result = placeFinderService.findVenuesNearNamedLocation(location, COSTA_COFFEE);

        assertThat("Venues not null", result, notNullValue());
        assertThat("Venues size 6", result, hasSize(6));

        verify(fourSquareClient).findPopularVenues(l1);
        verify(fourSquareClient).findRecommendedVenues(l1);
        verify(fourSquareClient).findPopularVenues(l2);
        verify(fourSquareClient).findRecommendedVenues(l2);

    }

    @Test
    public void canHandleEmptyRecs() {

        Venue venueA = createVenue("ID_1","VENUE_1", 1.0,1.0);
        Venue venueB = createVenue("ID_2","VENUE_2", 2.0,2.0);

        Location l1 = Location.builder().lng(1.0).lat(1.0).build();
        Location l2 = Location.builder().lng(2.0).lat(2.0).build();


        when(fourSquareClient.findVenueByName(location,COSTA_COFFEE)).thenReturn(Arrays.asList(venueA, venueB));

        when(fourSquareClient.findPopularVenues(l1)).thenReturn(Collections.emptyList());
        when(fourSquareClient.findRecommendedVenues(l1)).thenReturn(createPopularVenues());
        when(fourSquareClient.findPopularVenues(l2)).thenReturn(Collections.emptyList());
        when(fourSquareClient.findRecommendedVenues(l2)).thenReturn(Collections.emptyList());


        List<Venue> result = placeFinderService.findVenuesNearNamedLocation(location, COSTA_COFFEE);

        assertThat("Venues not null", result, notNullValue());
        assertThat("Venues size 3", result, hasSize(3));

        verify(fourSquareClient).findPopularVenues(l1);
        verify(fourSquareClient).findRecommendedVenues(l1);
        verify(fourSquareClient).findPopularVenues(l2);
        verify(fourSquareClient).findRecommendedVenues(l2);

    }

    @Test
    public void canHandleEmptyPopular() {

        Venue venueA = createVenue("ID_1","VENUE_1", 1.0,1.0);
        Venue venueB = createVenue("ID_2","VENUE_2", 2.0,2.0);

        Location l1 = Location.builder().lng(1.0).lat(1.0).build();
        Location l2 = Location.builder().lng(2.0).lat(2.0).build();

        when(fourSquareClient.findVenueByName(location,COSTA_COFFEE)).thenReturn(Arrays.asList(venueA, venueB));

        when(fourSquareClient.findPopularVenues(l1)).thenReturn(createRecommendedVenues());
        when(fourSquareClient.findRecommendedVenues(l1)).thenReturn(Collections.emptyList());

        when(fourSquareClient.findPopularVenues(l2)).thenReturn(Collections.emptyList());
        when(fourSquareClient.findRecommendedVenues(l2)).thenReturn(Collections.emptyList());


        List<Venue> result = placeFinderService.findVenuesNearNamedLocation(location, COSTA_COFFEE);

        assertThat("Venues not null", result, notNullValue());
        assertThat("Venues size 3", result, hasSize(3));

        verify(fourSquareClient).findPopularVenues(l1);
        verify(fourSquareClient).findRecommendedVenues(l1);
        verify(fourSquareClient).findPopularVenues(l2);
        verify(fourSquareClient).findRecommendedVenues(l2);

    }


    private List<Venue> createPopularVenues(){

        Venue popularVenue1 = createVenue("ID_3","POPULAR_VENUE_3", 3.0,3.0);
        Venue popularVenue2 = createVenue("ID_4","POPULAR_VENUE_4", 4.0,4.0);
        Venue popularVenue3 = createVenue("ID_5","POPULAR_VENUE_5", 5.0,5.0);

        return Arrays.asList(popularVenue1, popularVenue2, popularVenue3);
    }

    private List<Venue> createRecommendedVenues(){

        Venue popularVenue1 = createVenue("ID_3","RECOMMENDED_VENUE_1", 6.0,6.0);
        Venue popularVenue2 = createVenue("ID_4","RECOMMENDED_VENUE_2", 7.0,7.0);
        Venue popularVenue3 = createVenue("ID_5","RECOMMENDED_VENUE_3", 8.0,8.0);

        return Arrays.asList(popularVenue1, popularVenue2, popularVenue3);
    }


    private Venue createVenue(String id, String name, double lng, double lat ){

        return Venue.builder().id(id).name(name).Location(Location.builder().lng(lng).lat(lat).build()).build();
    }
}