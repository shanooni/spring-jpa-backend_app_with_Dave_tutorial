package io.shanoon.devtirospringjpaapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.shanoon.devtirospringjpaapplication.DTO.BookDTO;
import io.shanoon.devtirospringjpaapplication.Utils.TestUtils;
import io.shanoon.devtirospringjpaapplication.domain.Book;
import io.shanoon.devtirospringjpaapplication.service.BookService;
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
public class BookControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc,BookService bookService) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreatedBookSuccessfullyReturnHttp201Created() throws Exception {
        BookDTO testBook = TestUtils.createTestBookDto(null);
        String bookJson = objectMapper.writeValueAsString(testBook);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/v1/books/" + testBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreatedBookSuccessfullyReturnSavedBook() throws Exception {
        BookDTO testBook = TestUtils.createTestBookDto(null);
        String bookJson = objectMapper.writeValueAsString(testBook);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/v1/books/" + testBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("War and Peace")
        );
    }

    @Test
    public void testThatGetAllBookReturnHttp200StatusCode() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAllBooksReturnsListOfBooks() throws Exception {
        Book testBookByAuthorA = TestUtils.testBookByAuthorA(null);
        bookService.createBook(testBookByAuthorA.getIsbn(), testBookByAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("134-456-789")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("The Greatest")
        );
    }
}
