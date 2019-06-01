package com.thecatapi.downloader.integration.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CatControllerRuLocaleTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll_wrongLimitAndRuLocale_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"limit\":101}")
                .locale(Locale.forLanguageTag("ru")))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Limit должен быть между 1 и 100;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongSize_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"size\":\"asdfsaddfsaf\"}")
                .locale(Locale.forLanguageTag("ru")))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Допустимые значения для Size: full, med, small, thumb;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongOrder_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"order\":\"asdfsaddfsaf\"}")
                .locale(Locale.forLanguageTag("ru")))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Допустимые значения для Order: asc, desc, random;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongPage_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"page\":-1}")
                .locale(Locale.forLanguageTag("ru")))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Номер Page должен быть положительным или ноль;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongBreedId_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"breedId\":\"safdfsf\"}")
                .locale(Locale.forLanguageTag("ru")))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Неверное значение breedId. Смотрите https://docs.thecatapi.com/example-by-breed для дополнительной информации;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongMimeTypes_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"mimeTypes\":[\"fdsafsdf\"]}")
                .locale(Locale.forLanguageTag("ru")))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Допустимые значения для mimeTypes: jpg, png, gif;"))
                .andReturn();
    }

    @Test
    public void testGetAll_wrongCategoryIds_shouldReturn400Status() throws Exception {
        mockMvc.perform(post("/cats").contentType(APPLICATION_JSON_UTF8).content("{\"categoryIds\":[0]}")
                .locale(Locale.forLanguageTag("ru")))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Неподходящее значение categoryIds. Смотрите https://docs.thecatapi.com/api-reference/categories/categories-list для дополнительной информации;"))
                .andReturn();
    }
}

