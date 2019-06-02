package com.thecatapi.downloader.integration.controller;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class CatControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Value("${download.folder}")
    private String testFolder;

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
                .andExpect(content().string(Matchers.containsString(".jpg")))
                .andReturn();
    }

    @Test
    public void testGetAll_requestPng_shouldReturnPng() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"mimeTypes\":[\"png\"]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(".png")))
                .andReturn();
    }

    @Test
    public void testGetAll_requestGif_shouldReturnGif() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"mimeTypes\":[\"gif\"]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(".gif")))
                .andReturn();
    }

    @Test
    public void testGetAll_request2File_shouldReturn2File() throws Exception {
        MvcResult result = mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"limit\":2}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString().split(",").length, 2);
    }

    @Test
    public void testGetAll_request3Jpg_shouldReturn3Jpg() throws Exception {
        MvcResult result = mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8)
                .content("{\"limit\":3,\"mimeTypes\":[\"jpg\"]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Set<String> filePaths = new HashSet<>(Arrays.asList(result.getResponse().getContentAsString().split(",")));
        for (String filePath : filePaths) {
            assertTrue(filePath.contains(".jpg"));
        }
        assertEquals(filePaths.size(), 3);
    }

    @Test
    public void testGetAll_request3PngOrGif_shouldReturn3PngOrGif() throws Exception {
        MvcResult result = mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8)
                .content("{\"limit\":3,\"mimeTypes\":[\"png\",\"gif\"]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Set<String> filePaths = new HashSet<>(Arrays.asList(result.getResponse().getContentAsString().split(",")));
        for (String filePath : filePaths) {
            assertTrue(filePath.contains(".png") || filePath.contains(".gif"));
        }
        assertEquals(filePaths.size(), 3);
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
                .andExpect(jsonPath("$.message").value("Allowed values for Order: asc, desc, rand;"))
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

    @AfterEach
    private void deleteTestFolder() {
        try {
            FileUtils.deleteDirectory(new File(testFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

