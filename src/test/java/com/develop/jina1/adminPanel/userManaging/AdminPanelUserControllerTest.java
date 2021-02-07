package com.develop.jina1.adminPanel.userManaging;

import com.develop.jina1.security.WithCustomUserDetails;
import com.develop.jina1.user.UserProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@PropertySource("/application.properties")
@ActiveProfiles("test")
@WithCustomUserDetails(username = "anotherTestUser")
class AdminPanelUserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void change_userAuthorities_thenReturn_Ok() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/admin-panel/users/2/authorities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserProvider.getUserAuthCommand())))
                .andExpect(status().isOk());
    }
}