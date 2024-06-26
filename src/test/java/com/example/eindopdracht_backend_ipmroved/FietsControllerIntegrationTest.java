package com.example.eindopdracht_backend_ipmroved;

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
import com.example.eindopdracht_backend_ipmroved.entity.Fiets;


@SpringBootTest
@AutoConfigureMockMvc
public class FietsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void testCreateFietsUnauthorized() throws Exception {
        // Genereer een JWT-token voor een geautoriseerde gebruiker
        String jwtToken = jwtTokenProvider.generateToken("testuser", "ROLE_USER");

        String fietsJson = "{ \"id\": null, \"merk\": \"TestMerk\", \"model\": \"TestModel\", \"jaar\": 2024, \"frameMateriaal\": null, \"framemaat\": null, \"kleur\": null, \"fietsgarage\": null }";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/fietsen")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fietsJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateFietsAuthorized() throws Exception {
        // Genereer een JWT-token voor een geautoriseerde gebruiker
        String jwtToken = jwtTokenProvider.generateToken("testuser", "ROLE_USER");

        // Maak een nieuwe fiets aan via een POST-verzoek
        String fietsJson = "{ \"id\": null, \"merk\": \"TestMerk\", \"model\": \"TestModel\", \"jaar\": 2024, \"frameMateriaal\": null, \"framemaat\": null, \"kleur\": null, \"fietsgarage\": null }";
        MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/fietsen")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fietsJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        // Haal het aangemaakte fietsobject op uit de POST-response
        String responseContent = postResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Fiets createdFiets = mapper.readValue(responseContent, Fiets.class); // Import toegevoegd

        // Update het fietsobject via een PUT-verzoek
        createdFiets.setMerk("UpdatedMerk");
        String updatedFietsJson = mapper.writeValueAsString(createdFiets);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/fietsen/" + createdFiets.getId())
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedFietsJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.merk").value("UpdatedMerk"));
    }

    @Test
    public void testDeleteFietsAuthorized() throws Exception {
        // Genereer een JWT-token voor een geautoriseerde gebruiker
        String jwtToken = jwtTokenProvider.generateToken("testuser", "ROLE_USER");

        // Maak een nieuwe fiets aan via een POST-verzoek
        String fietsJson = "{ \"id\": null, \"merk\": \"TestMerk\", \"model\": \"TestModel\", \"jaar\": 2024, \"frameMateriaal\": null, \"framemaat\": null, \"kleur\": null, \"fietsgarage\": null }";
        MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/fietsen")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fietsJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        // Haal het aangemaakte fietsobject op uit de POST-response
        String responseContent = postResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Fiets createdFiets = mapper.readValue(responseContent, Fiets.class);

        // Verwijder het fietsobject via een DELETE-verzoek
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/fietsen/" + createdFiets.getId())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Controleer of het fietsobject niet meer bestaat via een GET-verzoek
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/fietsen/" + createdFiets.getId())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}