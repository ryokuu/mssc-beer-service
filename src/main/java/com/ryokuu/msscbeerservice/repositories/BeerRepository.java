package com.ryokuu.msscbeerservice.repositories;

import java.util.UUID;

import com.ryokuu.msscbeerservice.domain.Beer;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    
}
