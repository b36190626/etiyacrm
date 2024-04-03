package com.etiyacrm.customerservice.services.mappers;

import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.services.dtos.requests.CityRequests.CreateCityRequest;
import com.etiyacrm.customerservice.services.dtos.responses.CityResponses.CreatedCityResponse;
import com.etiyacrm.customerservice.services.dtos.responses.CityResponses.GetAllCityResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-03T13:34:40+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class CityMapperImpl implements CityMapper {

    @Override
    public GetAllCityResponse getAllCityResponseFromCity(City city) {
        if ( city == null ) {
            return null;
        }

        GetAllCityResponse getAllCityResponse = new GetAllCityResponse();

        getAllCityResponse.setId( city.getId() );
        getAllCityResponse.setName( city.getName() );

        return getAllCityResponse;
    }

    @Override
    public City cityFromCreateCityRequest(CreateCityRequest createCityRequest) {
        if ( createCityRequest == null ) {
            return null;
        }

        City city = new City();

        city.setName( createCityRequest.getName() );

        return city;
    }

    @Override
    public CreatedCityResponse createdCityResponseFromCity(City city) {
        if ( city == null ) {
            return null;
        }

        CreatedCityResponse createdCityResponse = new CreatedCityResponse();

        createdCityResponse.setId( city.getId() );
        createdCityResponse.setName( city.getName() );

        return createdCityResponse;
    }
}
