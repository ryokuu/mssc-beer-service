package com.ryokuu.msscbeerservice.services.inventory;

import com.ryokuu.msscbeerservice.bootstrap.BeerLoader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@Disabled
@SpringBootTest
public class BeerInvenoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp(){

    }

    @Test
    void testGetOnhandInventory() {
    // Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);

    //     System.out.println(qoh);
    }
}
