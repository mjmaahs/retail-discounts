package com.retaildiscounts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "cashier", roles = {"CASHIER"})
    void testCashierAccess() throws Exception {
        mockMvc.perform(
                        post("/api/calculate-net-amount")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"productSerialNumbers\":[\"000001\",\"000002\",\"000003\"],\"username\":\"bruce\"}")
                                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "manager", roles = {"MANAGER"})
    void testManagerAccess() throws Exception {
        mockMvc.perform(
                        post("/api/calculate-net-amount")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"productSerialNumbers\":[\"000001\",\"000002\",\"000003\"],\"username\":\"bruce\"}")
                                .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "customer", roles = {"CUSTOMER"})
    void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(
                        post("/api/calculate-net-amount")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"productSerialNumbers\":[\"000001\",\"000002\",\"000003\"],\"username\":\"bruce\"}")
                                .with(csrf()))
                .andExpect(status().isForbidden());
    }
}
