package com.develop.jina1.product;

import com.develop.jina1.security.WithCustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class ProductTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void get_product_thenReturn_Ok() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void get_nonexistentProduct_thenReturn_NotFound() throws Exception {
        mockMvc.perform(get("/api/products/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void get_productsWithEmptyFilter_thenReturn_Ok() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    void get_productsWithFilledFilter_thenReturn_Ok() throws Exception {
        mockMvc.perform(get("/api/products?minPrice=500&maxPrice=1000&categoryId=1&categoryCharIdList=1,2,3"))
                .andExpect(status().isOk());
    }

    @Test
    void create_product_thenReturn_Ok() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductProvider.getSmartphoneProductCommand())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryId", is(2)))
                .andExpect(jsonPath("$.name", is("IPhone")));
    }

    @Test
    void update_product_thenReturn_Ok() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/products/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductProvider.getHouseholdProductCommand())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Zinger")));
    }

    @Test
    void delete_product_thenReturn_NoContent() throws Exception {
        mockMvc.perform(delete("/api/products/4"))
                .andExpect(status().isNoContent());
    }
}