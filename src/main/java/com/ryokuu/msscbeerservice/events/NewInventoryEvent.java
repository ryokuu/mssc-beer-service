package com.ryokuu.msscbeerservice.events;

import com.ryokuu.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
    
}
