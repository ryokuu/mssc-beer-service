package com.ryokuu.msscbeerservice.services;

import java.util.UUID;
import java.util.stream.Collectors;

import com.ryokuu.msscbeerservice.domain.Beer;
import com.ryokuu.msscbeerservice.repositories.BeerRepository;
import com.ryokuu.msscbeerservice.web.controller.NotFoundException;
import com.ryokuu.msscbeerservice.web.mappers.BeerMapper;
import com.ryokuu.msscbeerservice.web.model.BeerDto;
import com.ryokuu.msscbeerservice.web.model.BeerPagedList;
import com.ryokuu.msscbeerservice.web.model.BeerStyleEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
    
    @Autowired
    private final BeerRepository beerRepository;
    @Autowired
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId, Boolean showInvetoryOnHand) {
        if (showInvetoryOnHand) {
            return beerMapper.beerToBeerDtoWithInvetory(
            beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }else{
            return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
        
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

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInvetoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (showInvetoryOnHand) {
            beerPagedList = new BeerPagedList(beerPage
            .getContent()
            .stream()
            .map(beerMapper::beerToBeerDtoWithInvetory)
            .collect(Collectors.toList()),
            PageRequest
                    .of(beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
            beerPage.getTotalElements());
        } else {
            beerPagedList = new BeerPagedList(beerPage
            .getContent()
            .stream()
            .map(beerMapper::beerToBeerDto)
            .collect(Collectors.toList()),
            PageRequest
                    .of(beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
            beerPage.getTotalElements());
        }
        return beerPagedList;
    }
    
}
