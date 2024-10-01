package org.openpay.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MarvelQueriesInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc.perform(get("/character"));
    }

    @Test
    void testGetMarvelQueries() throws Exception {
        this.mockMvc.perform(get("/marvelqueriesinfo"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].statusCode").exists())
                .andExpect(jsonPath("$[0].requestType").exists())
                .andExpect(jsonPath("$[0].url").exists())
                .andExpect(jsonPath("$[0].hash").exists())
                .andExpect(jsonPath("$[0].info").exists())
                .andExpect(jsonPath("$[0].requestDateTime").exists())
                .andExpect(jsonPath("$[0].responseDateTime").exists())
                .andExpect(jsonPath("$[0].result").exists());

    }
}
