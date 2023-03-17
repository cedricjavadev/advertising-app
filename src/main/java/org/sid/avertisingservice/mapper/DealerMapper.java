package org.sid.avertisingservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sid.avertisingservice.dtos.DealerDto;
import org.sid.avertisingservice.entities.Dealer;

@Mapper
public interface DealerMapper {

    DealerMapper INSTANCE = Mappers.getMapper(DealerMapper.class);

    @BeanMapping(ignoreByDefault  = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    DealerDto dealerToDealerDto(Dealer dealer);


    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Dealer dealerDtoToDealer(DealerDto dealerDto);
}
