package com.ryokuu.msscbeerservice.web.mappers;

import com.ryokuu.msscbeerservice.domain.Beer;
import com.ryokuu.msscbeerservice.web.model.BeerDto;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    
    BeerDto beerToBeerDto(Beer beer);
    BeerDto beerToBeerDtoWithInvetory(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
