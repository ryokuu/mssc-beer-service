package com.ryokuu.msscbeerservice.services;

import java.util.UUID;

import com.ryokuu.msscbeerservice.domain.Beer;
import com.ryokuu.msscbeerservice.repositories.BeerRepository;
import com.ryokuu.msscbeerservice.web.controller.NotFoundException;
import com.ryokuu.msscbeerservice.web.mappers.BeerMapper;
import com.ryokuu.msscbeerservice.web.model.BeerDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
    
    @Autowired
    private final BeerRepository beerRepository;
    @Autowired
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        
        return beerMapper.beerToBeerDto(
            beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
    
}
