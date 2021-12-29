package com.ryokuu.msscbeerservice.services;

import java.util.UUID;

import com.ryokuu.msscbeerservice.web.model.BeerDto;
import com.ryokuu.msscbeerservice.web.model.BeerPagedList;
import com.ryokuu.msscbeerservice.web.model.BeerStyleEnum;

import org.springframework.data.domain.PageRequest;


public interface BeerService {

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerDto getByUpc(String upc);
    
}
