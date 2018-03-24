package com.amcg.app;

import com.amcg.controller.PlaceController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PlaceController.class)
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPlaces() throws Exception {

        mockMvc.perform(get("/places?name=Costa"))
                .andExpect(status().isOk());

    }

    @Test
    public void expectBadRequestWhenNameIsMissing() throws Exception {

        mockMvc.perform(get("/places"))

                .andExpect(status().isBadRequest());

    }

    @Test
    public void expectBadRequestWhenNameIsEmpty() throws Exception {

        mockMvc.perform(get("/places?name="))
                .andExpect(status().isBadRequest());

    }


}