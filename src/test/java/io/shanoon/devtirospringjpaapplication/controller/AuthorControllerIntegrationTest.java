package io.shanoon.devtirospringjpaapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.shanoon.devtirospringjpaapplication.Utils.TestUtils;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.mapper = new ObjectMapper();
    }

    @Test
    public void testThatAuthorCreatedSuccessfulReturnHttp201Created() throws Exception {
        Author author = TestUtils.testAuthorC();
        author.setAuthorId(null);
        String jsonAuthor = mapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAuthor)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatAuthorIsCreatedAndReturnsSavedAuthor() throws Exception {

        Author author = TestUtils.testAuthorC();
        author.setAuthorId(null);
        String jsonAuthor = mapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAuthor)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(45)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Fred Amo")
        );
    }
}
