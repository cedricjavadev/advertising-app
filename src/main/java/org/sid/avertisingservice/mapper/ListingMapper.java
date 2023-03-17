package org.sid.avertisingservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sid.avertisingservice.dtos.ListingDto;
import org.sid.avertisingservice.entities.Listing;

@Mapper
public interface ListingMapper {

    ListingMapper INSTANCE = Mappers.getMapper(ListingMapper.class);

    @BeanMapping(ignoreByDefault  = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dealerId", target = "dealerId")
    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "state", target = "state")
    ListingDto listingToListingDto(Listing listing);


    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dealerId", target = "dealerId")
    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "state", target = "state")
    Listing listingDtoToListing(ListingDto listingDto);
}
