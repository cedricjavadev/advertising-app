package org.sid.avertisingservice.service;

import lombok.extern.slf4j.Slf4j;
import org.sid.avertisingservice.dtos.DealerDto;
import org.sid.avertisingservice.entities.Dealer;
import org.sid.avertisingservice.exceptions.DealerNotFoundException;
import org.sid.avertisingservice.mapper.DealerMapper;
import org.sid.avertisingservice.repository.DealerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class DealerServiceIml implements DealerService{

    private final DealerRepository dealerRepository;

    public DealerServiceIml(DealerRepository dealerRepository){
        this.dealerRepository = dealerRepository;
    }

    @Override
    public DealerDto createDealer(DealerDto dealerDto) {

        Dealer dealer = DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto);

        return DealerMapper.INSTANCE.dealerToDealerDto(dealerRepository.save(dealer));
    }

    @Override
    public DealerDto getDealerById(String dealerId) throws DealerNotFoundException {

        Dealer dealer = dealerRepository.findById(dealerId).orElseThrow(() -> new DealerNotFoundException("Dealer Not Found"));

        return DealerMapper.INSTANCE.dealerToDealerDto(dealer);
    }

    @Override
    public DealerDto updateDealer(DealerDto dealerDto) {

        Dealer dealer = DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto);

        return DealerMapper.INSTANCE.dealerToDealerDto(dealerRepository.save(dealer));
    }

    @Override
    public void deleteDealerById(String dealerId) {

        dealerRepository.deleteById(dealerId);
    }

    @Override
    public List<DealerDto> getAllDealer() {

        return dealerRepository.findAll().stream().map(DealerMapper.INSTANCE::dealerToDealerDto).collect(Collectors.toList());
    }
}
