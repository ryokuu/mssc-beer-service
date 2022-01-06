package com.ryokuu.common.events;

import java.io.Serializable;

import com.ryokuu.msscbeerservice.web.model.BeerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable{

    static final long serialVersionID = -5815562340065181210L;
    
    private BeerDto beerDto;
}
