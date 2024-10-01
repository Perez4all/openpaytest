package org.openpay.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCharacters() throws Exception {
        String expectedJson = Files.readString(Paths.get(this.getClass().getClassLoader().getResource("charactersResponse.json").toURI()));

        this.mockMvc.perform(get("/character"))
                .andExpect(content().json(expectedJson));

    }

    @Test
    void testGetCharacterById() throws Exception {
        String expectedJson = Files.readString(Paths.get(this.getClass().getClassLoader().getResource("characterByIdResponse.json").toURI()));

        this.mockMvc.perform(get("/character/1011334"))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedJson));

    }
}
