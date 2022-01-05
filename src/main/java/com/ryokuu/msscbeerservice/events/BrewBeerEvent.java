package com.ryokuu.msscbeerservice.events;

import com.ryokuu.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
    
}
