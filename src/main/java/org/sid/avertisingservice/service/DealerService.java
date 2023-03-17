package org.sid.avertisingservice.service;

import org.sid.avertisingservice.dtos.DealerDto;
import org.sid.avertisingservice.exceptions.DealerNotFoundException;

import java.util.List;
import java.util.UUID;

public interface DealerService {

    DealerDto createDealer(DealerDto dealerDto);

    DealerDto getDealerById(String dealerId) throws DealerNotFoundException;

    DealerDto updateDealer(DealerDto dealer);

    void deleteDealerById(String dealerId);

    List<DealerDto> getAllDealer();
}
