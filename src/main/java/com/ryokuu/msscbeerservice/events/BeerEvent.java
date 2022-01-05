package com.ryokuu.msscbeerservice.events;

import java.io.Serializable;

import com.ryokuu.msscbeerservice.web.model.BeerDto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable{

    static final long serialVersionID = -5815562340065181210L;
    
    private final BeerDto beerDto;
}
