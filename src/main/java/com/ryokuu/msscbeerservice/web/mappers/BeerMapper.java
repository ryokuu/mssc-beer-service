package com.ryokuu.msscbeerservice.web.mappers;

import com.ryokuu.msscbeerservice.domain.Beer;
import com.ryokuu.msscbeerservice.web.model.BeerDto;

import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
