package com.ryokuu.msscbeerservice.services.brewing;

import java.util.List;

import com.ryokuu.common.events.BrewBeerEvent;
import com.ryokuu.msscbeerservice.config.JmsConfig;
import com.ryokuu.msscbeerservice.domain.Beer;
import com.ryokuu.msscbeerservice.repositories.BeerRepository;
import com.ryokuu.msscbeerservice.services.inventory.BeerInventoryService;
import com.ryokuu.msscbeerservice.web.mappers.BeerMapper;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());

            log.debug("min on hand : " + beer.getMinOnHand());
            log.debug("inventory is : "+ invQOH);

            if(beer.getMinOnHand() >=invQOH){
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
