package com.book.library;

import com.library.book.model.Book;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;


class LibraryApplicationTests extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void addBook() throws Exception {
        String uri = "/api/v1/books/";
        Book bookObj = new Book();

        bookObj.setName("Book of Eli");
        bookObj.setPublished("21-5-2020");
        bookObj.setRating(5);

        String inputJson = super.mapToJson(bookObj);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

    }

    @Test
    public void getAllBooksCount() throws Exception {
        String uri = "/api/v1/books/all";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Book[] bookList = super.mapFromJson(content, Book[].class);
        System.out.println(content);
        System.out.println(bookList.length);
        assertTrue(bookList.length!=0);
    }

    @Test
    public void getBookWithId() throws Exception {
        String uri = "/api/v1/books/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Book book = super.mapFromJson(content, Book.class);
        System.out.println(content);
        System.out.println(book.getId());
        assertEquals(1, book.getId().intValue());
    }
}
