package com.example.firststepsspring.dto.mappers;

import com.example.firststepsspring.dto.CreateSweetDTO;
import com.example.firststepsspring.model.Sweet;
import org.springframework.stereotype.Component;

@Component
public class CreateSweetMapper {

    public Sweet fromDTO (CreateSweetDTO createSweetDTO) {
        return Sweet.builder()
                .id(createSweetDTO.getId())
                .color(createSweetDTO.getColor())
                .build();
    }
}
