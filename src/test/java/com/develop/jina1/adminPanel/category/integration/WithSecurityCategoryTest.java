package com.develop.jina1.adminPanel.category.integration;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@PropertySource("/application.properties")
@ActiveProfiles("test")
public class WithSecurityCategoryTest {

    private String tokenPrefix;
    private String token;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void initAuthToken() throws Exception {
        tokenPrefix = "Bearer ";
        MockHttpServletResponse response = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testUser\", \"password\":\"testPass\"}"))
                .andReturn().getResponse();

        if (response.getStatus() == HttpServletResponse.SC_OK) {
            JSONObject jsonObject = new JSONObject(response.getContentAsString());
            token = jsonObject.getString("token");
        }
    }

    @Test
    void getCategory_thenReturnOk() throws Exception {
        assertThat(token).isNotBlank();
        mockMvc.perform(get("/api/categories/1")
                .header("Authorization", tokenPrefix + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }
}
