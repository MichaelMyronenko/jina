package com.develop.jina1.product.productCharacteristic;

import com.develop.jina1.security.WithCustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
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
class ProdCharValueTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void get_productCharacteristicValue_thenReturn_Ok() throws Exception {
        mockMvc.perform(get("/api/products/1/characteristicValues"))
                .andExpect(status().isOk());
    }

    @Test
    void save_productCharacteristicValue_thenReturn_Created() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/products/1/characteristicValues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProdCharValueProvider.getProdCharValueCommand())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryCharacteristicValueId", is(3)));
    }

    @Test
    void update_productCharacteristicValue_thenReturn_Ok() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/products/2/characteristicValues/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProdCharValueProvider.getSecProdCharValueCommand())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryCharacteristicValueId", is(1)));
    }

    @Test
    void get_productCharValuesByCategory_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/products/1/characteristicValues/1"))
                .andExpect(status().isOk());
    }

    @Test
    void get_nonexistentProductCharValuesByCategory_thenReturn_NotFound() throws Exception {
        mockMvc.perform(get("/api/products/1/characteristicValues/100"))
                .andExpect(status().isNotFound());
    }
}