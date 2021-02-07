package com.develop.jina1.adminPanel.category.categoryCharacteristic.integration;

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
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@PropertySource("/application.properties")
@ActiveProfiles("test")
@WithCustomUserDetails
class CategoryCharValueControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void get_categoryCharValue_thenReturn_Ok() throws Exception {
        mockMvc.perform(get("/api/categories/1/characteristicValues/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void get_nonExistentCategoryCharValue_thenReturn_NotFound() throws Exception {
        mockMvc.perform(get("/api/categories/1/characteristicValues/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void get_categoryCharValues_thenReturn_Ok() throws Exception {
        mockMvc.perform(get("/api/categories/1/characteristicValues"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void create_categoryCharValue_thenReturn_Ok() throws Exception {
        mockMvc.perform(post("/api/categories/1/characteristicValues")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\":\"test value\", \"characteristicId\":\"1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.value", is("test value")));
    }

    @Test
    void tryToCreate_ExistentCategoryCharValue_thenReturnConflict() throws Exception {
        mockMvc.perform(post("/api/categories/1/characteristicValues")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\":\"Intel core 7gen\", \"characteristicId\":\"1\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void update_categoryCharValue_thenReturnOk() throws Exception {
        mockMvc.perform(put("/api/categories/1/characteristicValues/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\":\"test update value\", \"characteristicId\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", is("test update value")))
                .andExpect(jsonPath("$.characteristicId", is(1)));
    }

    @Test
    void update_CategoryCharValue_tryToSetExistentValue_thenReturnConflict() throws Exception {
        mockMvc.perform(put("/api/categories/1/characteristicValues/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\":\"Intel core 7gen\", \"characteristicId\":\"1\"}"))
                .andExpect(status().isConflict());
    }
}