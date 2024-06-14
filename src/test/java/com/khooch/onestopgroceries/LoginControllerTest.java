package com.khooch.onestopgroceries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.khooch.onestopgroceries.controller.LoginController;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    @WithMockUser // Simulate an authenticated user
    public void testLoginSubmit_authenticated() throws Exception {
        // Perform POST request to /login
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("admin", "12345")
                .param("user2", "12345")
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expecting HTTP status OK (200)
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"));
    }

    @Test
    public void testLoginSubmit_notAuthenticated() throws Exception {
        // Clear any existing authentication
        SecurityContextHolder.clearContext();

        // Perform POST request to /login
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username", "testuser")
                .param("password", "testpassword")
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expecting HTTP status OK (200)
                .andExpect(MockMvcResultMatchers.view().name("login")) // Expecting view name "login"
                .andExpect(MockMvcResultMatchers.model().attributeExists("error")); // Expecting "error" attribute in model
    }
}
