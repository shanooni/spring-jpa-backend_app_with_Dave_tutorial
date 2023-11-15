package io.shanoon.devtirospringjpaapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.shanoon.devtirospringjpaapplication.DTO.AuthorDTO;
import io.shanoon.devtirospringjpaapplication.Utils.TestUtils;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import io.shanoon.devtirospringjpaapplication.service.AuthorService;
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

    private AuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
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
                MockMvcResultMatchers.jsonPath("$.authorId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(45)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Fred Amo")
        );
    }

    @Test
    public void testThatListAuthorsReturnHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsReturnListAuthors() throws Exception {
        Author testAuthor = TestUtils.testAuthorC();
        authorService.save(testAuthor);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].authorId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(45)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Fred Amo")
        );
    }

    @Test
    public void testThatGetAuthorsReturnHttpStatus200WhenAuthorExist() throws Exception {
        Author testAuthor = TestUtils.testAuthorC();
        authorService.save(testAuthor);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/authors/"+testAuthor.getAuthorId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorsReturnHttpStatus404WhenAuthorDoesNotExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/authors/90")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorsReturnSavedAuthor() throws Exception {
        Author testAuthor = TestUtils.testAuthorA();
        authorService.save(testAuthor);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/authors/"+testAuthor.getAuthorId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.authorId").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(37)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("John Doe")
        );
    }

    @Test
    public void testThatUpdateWithNonExistingAuthorIdReturnsHttpStatusCode404() throws Exception {
        AuthorDTO author = TestUtils.testAuthorDto();
        String authorJson = mapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/authors/000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }
    @Test
    public void testThatUpdateWithExistingAuthorIdReturnsHttpStatusCode200() throws Exception {
        Author authorC = TestUtils.testAuthorC();
        Author saveAuthor = authorService.save(authorC);

        AuthorDTO author = TestUtils.testAuthorDto();
        String authorJson = mapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/authors/"+ saveAuthor.getAuthorId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatUpdateWithExistingAuthorIdReturnsSavedAuthor() throws Exception {
        Author authorC = TestUtils.testAuthorC();
        Author saveAuthor = authorService.save(authorC);

        AuthorDTO author = TestUtils.testAuthorDto();
        author.setAuthorId(saveAuthor.getAuthorId());

        String authorJson = mapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/authors/"+ saveAuthor.getAuthorId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(author.getAge())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(author.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.authorId").value(saveAuthor.getAuthorId())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatusCode2000k() throws Exception {
        Author authorC = TestUtils.testAuthorC();
        Author saveAuthor = authorService.save(authorC);

        AuthorDTO author = TestUtils.testAuthorDto();
        author.setAuthorId(saveAuthor.getAuthorId());

        String authorJson = mapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/authors/"+ saveAuthor.getAuthorId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
        Author authorC = TestUtils.testAuthorC();
        Author saveAuthor = authorService.save(authorC);

        AuthorDTO author = TestUtils.testAuthorDto();
        author.setAuthorId(saveAuthor.getAuthorId());
        author.setName("Shanoon Issaka");

        String authorJson = mapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/authors/"+ saveAuthor.getAuthorId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Shanoon Issaka")
        );
    }
}
