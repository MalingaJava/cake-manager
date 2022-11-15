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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

//    @Test
//    public void testGETCakes() throws Exception {
//        String json = new ObjectMapper().writeValueAsString(singletonList(savedCake));
//        mockMvc.perform(get("/cakes/"))
//                .andExpect(status().isOk())
//                .andExpect(result -> equals(json));
//    }
//
//    @Test
//    public void testPOSTCreatesCake() throws Exception {
//
//        String name = "new test cake via rest";
//
//        CakeDTO cake = new CakeDTO(
//                name,
//                "a test cake to be saved",
//                "http://image_url");
//        String json = new ObjectMapper().writeValueAsString(cake);
//
//        MvcResult mvcResult = mockMvc.perform(post("/cakes").contentType(MediaType.APPLICATION_JSON).content(json))
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", containsString("/cake/")))
//                .andReturn();
//
//        String location = mvcResult.getResponse().getHeader("Location");
//
//        // Location header is of the form /cake/id
//        String[] parts = location.split("/cake/");
//        int id = Integer.parseInt(parts[1]);
//
//        Optional<CakeDTO> newCake = repository.findById(id);
//        assertTrue("the cake was not persisted", newCake.isPresent());
//        assertEquals(name, newCake.get().getName());
//    }

    @DisplayName("Testing get cakes endpoint success")
    @Test
    public void testGETCake() throws Exception {
        CakeDTO savedCake = new CakeDTO(
                "Testing cake",
                "Testing cake created by init scripts",
                "www.google.com");

        mockMvc.perform(get("/cakes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.cakes").isArray())
                .andExpect(jsonPath("$.cakes[0].name").value("Testing cake"));
    }
}
