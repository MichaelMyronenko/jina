package com.develop.jina1.adminPanel.category.integration;

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
public class MockSecurityCategoryTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getCategory() throws Exception {
        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void getNonexistentCategory_thenReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/categories/1000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCategories_withFilledFilters_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/categories?title=Smartphones&id=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getCategories_withoutFilters_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void createCategory_thenReturnCreated() throws Exception {
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test category\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Test category")));
    }

    @Test
    void tryToCreateExistentCategory_thenReturnConflictException() throws Exception {
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Smartphones\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void updateCategory_thenReturnOk() throws Exception {
        mockMvc.perform(put("/api/categories/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test update category\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.title", is("Test update category")));
    }

    @Test
    void updateCategory_tryToSetExistentTitle_thenReturn_Conflict() throws Exception {
        mockMvc.perform(put("/api/categories/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Smartphones\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void deleteCategory_thenReturnOk() throws Exception{
        mockMvc.perform(delete("/api/categories/4"))
                .andExpect(status().isNoContent());
    }
}
