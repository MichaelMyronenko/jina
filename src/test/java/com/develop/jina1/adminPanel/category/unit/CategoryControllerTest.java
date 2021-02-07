package com.develop.jina1.adminPanel.category.unit;

import com.develop.jina1.adminPanel.category.CategoryController;
import com.develop.jina1.adminPanel.category.CategoryProvider;
import com.develop.jina1.adminPanel.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@Import(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void getCategory() throws Exception {
        when(categoryService.getCategory(any()))
                .thenReturn(CategoryProvider.getCategoryDto("Laptop"));

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is("Laptop")))
                .andExpect(jsonPath("id", is(1)));
    }

    @Test
    void getCategories() throws Exception {
        when(categoryService.getCategories(any()))
                .thenReturn(CategoryProvider.getCategoryDtoList());

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void createCategory() throws Exception {
        when(categoryService.createCategory(any()))
                .thenReturn(CategoryProvider.getCategoryDto("Alcohol"));

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Alcohol\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("title", is("Alcohol")));
    }

    @Test
    void updateCategory() throws Exception {
        when(categoryService.updateCategory(any(), any()))
                .thenReturn(CategoryProvider.getCategoryDto("Laptop"));

        mockMvc.perform(put("/api/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Laptop\"}"))
                .andExpect(jsonPath("title", is("Laptop")));
    }

    @Configuration
    @EnableWebSecurity
    static class TestSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests().anyRequest().anonymous();
        }
    }
}