package com.thecatapi.downloader.integration.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CatControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll_emptyRequest_shouldReturn200Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetAll_requestJpg_shouldReturnJpg() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"mimeTypes\":[\"jpg\"]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("jpg")))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongLimit_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"limit\":101}"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Limit must be between 1 and 100;"))
                .andReturn();
    }


    @Test
    public void testGetAll_wrongSize_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"size\":\"asdfsaddfsaf\"}"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Allowed values for Size: full, med, small, thumb;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongOrder_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"order\":\"asdfsaddfsaf\"}"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Allowed values for Order: asc, desc, random;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongPage_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"page\":-1}"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Page number must be positive or zero;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongBreedId_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"breedId\":\"safdfsf\"}"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Wrong value of breedId. See https://docs.thecatapi.com/example-by-breed for more information;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongMimeTypes_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"mimeTypes\":[\"fdsafsdf\"]}"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Allowed values for mimeTypes: jpg, png, gif;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongCategoryIds_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"categoryIds\":[0]}"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Wrong value of categoryIds. See https://docs.thecatapi.com/api-reference/categories/categories-list for more information;"))
                .andReturn();
    }





}

