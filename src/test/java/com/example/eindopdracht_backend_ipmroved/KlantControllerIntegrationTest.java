package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.entity.Klant;
import com.example.eindopdracht_backend_ipmroved.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class KlantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Initialiseer ObjectMapper

    @Test
    public void testCreateKlant() throws Exception {
        String jwtToken = jwtTokenProvider.generateToken("testuser", "ROLE_USER");

        String klantJson = "{ \"id\": null, \"voornaam\": \"John\", \"achternaam\": \"Doe\", \"email\": \"john.doe@example.com\", \"telefoonnummer\": \"0123456789\", \"adres\": \"Teststraat 1\", \"plaats\": \"Teststad\", \"postcode\": \"1234 AB\", \"premium\": false, \"aankoopGeschiedenis\": [] }";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/klanten")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(klantJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }



    @Test
    public void testUpdateKlant() throws Exception {
        String jwtToken = jwtTokenProvider.generateToken("testuser", "ROLE_USER");

        String klantJson = "{ \"id\": null, \"voornaam\": \"John\", \"achternaam\": \"Doe\", \"email\": \"john.doe@example.com\", \"telefoonnummer\": \"0123456789\", \"adres\": \"Teststraat 1\", \"plaats\": \"Teststad\", \"postcode\": \"1234 AB\", \"premium\": false, \"aankoopGeschiedenis\": [] }";
        MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/klanten")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(klantJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String responseContent = postResult.getResponse().getContentAsString();
        Klant createdKlant = objectMapper.readValue(responseContent, Klant.class);

        createdKlant.setAdres("Nieuwe Teststraat 2");
        String updatedKlantJson = objectMapper.writeValueAsString(createdKlant);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/klanten/" + createdKlant.getId())
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedKlantJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.adres").value("Nieuwe Teststraat 2"));
    }

    @Test
    public void testDeleteKlant() throws Exception {
        String jwtToken = jwtTokenProvider.generateToken("testuser", "ROLE_USER");

        String klantJson = "{ \"id\": null, \"voornaam\": \"John\", \"achternaam\": \"Doe\", \"email\": \"john.doe@example.com\", \"telefoonnummer\": \"0123456789\", \"adres\": \"Teststraat 1\", \"plaats\": \"Teststad\", \"postcode\": \"1234 AB\", \"premium\": false, \"aankoopGeschiedenis\": [] }";

        MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/klanten")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(klantJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String responseContent = postResult.getResponse().getContentAsString();
        Klant createdKlant = objectMapper.readValue(responseContent, Klant.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/klanten/" + createdKlant.getId())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/klanten/" + createdKlant.getId())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
