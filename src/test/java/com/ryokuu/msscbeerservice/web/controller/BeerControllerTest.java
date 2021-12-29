package com.ryokuu.msscbeerservice.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryokuu.msscbeerservice.bootstrap.BeerLoader;
import com.ryokuu.msscbeerservice.services.BeerService;
import com.ryokuu.msscbeerservice.web.model.BeerDto;
import com.ryokuu.msscbeerservice.web.model.BeerStyleEnum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void testGetBeerById() throws Exception {

        given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSaveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
            .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void testUpdateByBeer() throws Exception{
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID().toString())
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    BeerDto getValidBeerDto(){
        return BeerDto.builder()
            .beerName("My Beer")
            .beerStyle(BeerStyleEnum.ALE)
            .price(new BigDecimal("2.99"))
            .upc(BeerLoader.BEER_1_UPC)
            .build();
    }

}
