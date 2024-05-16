package com.etiya.catalogservice.services.concretes;

import com.etiya.catalogservice.services.abstracts.CharacteristicService;
import com.etiya.catalogservice.services.dtos.requests.characteristicRequests.CreateCharacteristicRequest;
import com.etiya.catalogservice.services.dtos.requests.characteristicRequests.UpdateCharacteristicRequest;
import com.etiya.catalogservice.services.dtos.responses.characteristicResponses.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CharacteristicServiceImpl implements CharacteristicService {
    @Override
    public CreatedCharacteristicResponse add(CreateCharacteristicRequest createCharacteristicRequest) {
        return null;
    }

    @Override
    public UpdatedCharacteristicResponse update(UpdateCharacteristicRequest updateCharacteristicRequest, String id) {
        return null;
    }

    @Override
    public List<GetAllCharacteristicResponse> getAll() {
        return null;
    }

    @Override
    public GetCharacteristicResponse getById(String id) {
        return null;
    }

    @Override
    public DeletedCharacteristicResponse delete(String id) {
        return null;
    }
}
