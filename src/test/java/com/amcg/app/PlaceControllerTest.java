package com.amcg.app;

import com.amcg.controller.PlaceController;
import com.amcg.model.Location;
import com.amcg.model.Venue;
import com.amcg.service.PlaceFinderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(PlaceController.class)
public class PlaceControllerTest {

    public static final String VENUE_ID = "ID1";
    public static final String COSTA_COFFEE = "Costa";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaceFinderService placeFinderService;

    @Test
    public void getPlaces() throws Exception {


        when(placeFinderService.findVenuesNearNamedLocation(COSTA_COFFEE)).thenReturn(Arrays.asList(createVenue()));
        MvcResult result = mockMvc.perform(get("/places?name=Costa")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mockMvc
                .perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.venues", hasSize(1)))
                .andExpect(jsonPath("$.venues[0].id").value(VENUE_ID))
                .andExpect(jsonPath("$.venues[0].name").value(COSTA_COFFEE))
                .andExpect(jsonPath("$.venues[0].location.longitude").value(51.4826))
                .andExpect(jsonPath("$.venues[0].location.latitude").value(0.0077));


        verify(placeFinderService).findVenuesNearNamedLocation(COSTA_COFFEE);


    }

    @Test
    public void canHandleEmptyResponse() throws Exception {

        when(placeFinderService.findVenuesNearNamedLocation(COSTA_COFFEE)).thenReturn(Collections.emptyList());
        MvcResult result = mockMvc.perform(get("/places?name=Costa")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mockMvc
                .perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.venues", hasSize(0)));

        verify(placeFinderService).findVenuesNearNamedLocation(COSTA_COFFEE);
    }


    @Test
    public void expectBadRequestWhenNameIsMissing() throws Exception {

        mockMvc.perform(get("/places"))

                .andExpect(status().isBadRequest());

        verify(placeFinderService, never()).findVenuesNearNamedLocation(anyString());

    }

    @Test
    public void expectBadRequestWhenNameIsEmpty() throws Exception {


        mockMvc.perform(get("/places?name="))
                .andExpect(status().isBadRequest());

        verify(placeFinderService, never()).findVenuesNearNamedLocation(anyString());

    }


    private Venue createVenue(){

        Location location = new Location(51.4826,0.0077);
        return new Venue(VENUE_ID, COSTA_COFFEE,location);
    }


}