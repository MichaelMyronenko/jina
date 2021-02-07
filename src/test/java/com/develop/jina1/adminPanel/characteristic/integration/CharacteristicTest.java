package com.develop.jina1.adminPanel.characteristic.integration;

import com.develop.jina1.security.WithCustomUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@PropertySource("/application.properties")
@ActiveProfiles("test")
@WithCustomUserDetails
class CharacteristicTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void get_characteristic_thenReturn_Ok() throws Exception {
        mockMvc.perform(get("/api/characteristics/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void get_nonexistentCharacteristic_thenReturn_NotFound() throws Exception {
        mockMvc.perform(get("/api/characteristics/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_characteristic_thenReturn_Created() throws Exception {
        mockMvc.perform(post("/api/characteristics")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test characteristic\", \"isAdditional\":\"false\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("test characteristic")));
    }

    @Test
    void tryToCreate_existentCharacteristic_thenReturn_Conflict() throws Exception {
        mockMvc.perform(post("/api/characteristics")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Processor\", \"isAdditional\":\"false\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void update_characteristic_thenReturn_Ok() throws Exception {
        mockMvc.perform(put("/api/characteristics/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test update name\", \"isAdditional\":\"false\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test update name")));
    }

    @Test
    void update_characteristic_tryToSet_existentName_thenReturn_Conflict() throws Exception {
        mockMvc.perform(put("/api/characteristics/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Processor\", \"isAdditional\":\"false\"}"))
                .andExpect(status().isConflict());
    }
}