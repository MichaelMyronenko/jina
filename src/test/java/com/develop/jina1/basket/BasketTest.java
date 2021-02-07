package com.develop.jina1.basket;

import com.develop.jina1.security.WithCustomUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@PropertySource("/application.properties")
@ActiveProfiles("test")
@WithCustomUserDetails
class BasketTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getCategory() throws Exception {
        mockMvc.perform(get("/api/basket"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)));
    }

    @Test
    void add_product_thenReturn_Ok() throws Exception {
        mockMvc.perform(post("/api/basket")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"productId\":\"1\", \"quantity\":\"2\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)));
    }

    @Test
    void remove_product_thenReturn_Ok() throws Exception {
        mockMvc.perform(delete("/api/basket/products/2"))
                .andExpect(status().isOk());
    }
}