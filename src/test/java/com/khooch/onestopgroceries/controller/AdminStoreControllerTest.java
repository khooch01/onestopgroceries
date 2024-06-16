package com.khooch.onestopgroceries.controller;

import com.khooch.onestopgroceries.configuration.WebSecurityConfiguration;
import com.khooch.onestopgroceries.entity.Store;
import com.khooch.onestopgroceries.service.MyUserDetailsService;
import com.khooch.onestopgroceries.service.StoreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminStoreController.class)
@Import(WebSecurityConfiguration.class) // Import the security configuration
public class AdminStoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private StoreService storeService;

    @Test
    @WithMockUser(username = "admin", authorities = {"ADD_STORE"})
    public void givenAdminUser_whenGetAdminStores_thenOk() throws Exception {
        Mockito.when(storeService.getAllStores()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/admin/stores"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenUserWithoutRole_whenGetAdminStores_thenRedirectToLogin() throws Exception {
        mockMvc.perform(get("/admin/stores"))
                .andExpect(status().isFound()) // 302 status for redirection
                .andExpect(redirectedUrlPattern("**/login")); // Ensure it redirects to the login page
    }

    @Test
    @WithMockUser(username = "user", authorities = {"VIEW_STORE"})
    public void givenUserWithViewStoreRole_whenGetStoreDetails_thenForbidden() throws Exception {
        mockMvc.perform(get("/admin/stores/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADD_STORE"})
    public void givenAdminUser_whenGetStoreDetails_thenOk() throws Exception {
        Store store = new Store();
        store.setName("Test Store");
        Mockito.when(storeService.getStoreById(1L)).thenReturn(store);
        mockMvc.perform(get("/admin/stores/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("store"));
    }

    @Test
    @WithMockUser(username = "user", authorities = {})
    public void givenUserWithNoAuthorities_whenGetAdminStores_thenForbidden() throws Exception {
        mockMvc.perform(get("/admin/stores"))
                .andExpect(status().isForbidden());
    }
}