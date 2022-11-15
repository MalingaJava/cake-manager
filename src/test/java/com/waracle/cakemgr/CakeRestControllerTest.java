package com.waracle.cakemgr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CakeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CakeRepository repository;

    @BeforeAll
    static void beforeAll() {
    }

    @AfterAll
    static void afterAll() {
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Testing add cakes endpoint success")
    @Test
    public void testAddCakeSuccess() throws Exception {
        CakeDTO cake = new CakeDTO(
                "Testing cake added by test suite",
                "Testing cake created by test suite",
                "www.google.com");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cakes")
                        .content(asJsonString(cake))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/cake/")))
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        // Location header is of the form /cake/id
        String[] parts = Objects.requireNonNull(location).split("/cake/");
        int id = Integer.parseInt(parts[1]);

        Optional<CakeDTO> addedCake = repository.findById(id);
        assertTrue("Saving cake failed", addedCake.isPresent());
        assertEquals("Testing cake added by test suite", addedCake.get().getName());
    }

    @DisplayName("Testing add cakes endpoint validation fail")
    @Test
    public void testAddCakeFail() throws Exception {
        CakeDTO cake = new CakeDTO(
                "Testing cake added by test suitehfjdsfhdjfhjskdfhsjkdfhdsjkfhsdjkfhdsjkfhsdjfhsd",
                "Testing cake created by test suite",
                "www.google.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/cakes")
                        .content(asJsonString(cake))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @DisplayName("Testing get cakes endpoint success")
    @Test
    public void testGETCake() throws Exception {
        mockMvc.perform(get("/cakes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.cakes").isArray())
                .andExpect(jsonPath("$.cakes[0].name").value("Testing cake"))
                .andExpect(jsonPath("$.cakes[0].description").value(
                        "Testing cake created by init scripts"))
                .andExpect(jsonPath("$.cakes[0].image").value("www.google.com"))
                .andExpect(jsonPath("$.cakes[1].name").value("Testing cake2"))
                .andExpect(jsonPath("$.cakes[1].description").value(
                        "Testing cake created by init scripts2"))
                .andExpect(jsonPath("$.cakes[1].image").value("www.google2.com"));
    }

    @DisplayName("Testing invalid cake endpoint")
    @Test
    public void testInvalidCake() throws Exception {
        mockMvc.perform(get("/cakes-invalid")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @DisplayName("Testing / cake endpoint with Thymeleaf")
    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("cakeList", notNullValue()))
                .andExpect(content().string(containsString("Cake manager list of cakes")));
    }

}
