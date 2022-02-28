package com.example.firststepsspring.dto.mappers;

import com.example.firststepsspring.dto.SweetDTO;
import com.example.firststepsspring.model.Sweet;
import org.springframework.stereotype.Component;

@Component
public class SweetMapper {

    //forma builder
    public SweetDTO sweetToDTO(Sweet sweet) {
        return SweetDTO.builder()
                .id(sweet.getId())
                .color(sweet.getColor())
                .gnome(sweet.getGnome())
                .gnomeId(sweet.getGnome().getId())
                .build();
    }

    //forma no builder
    public Sweet sweetFromDTO(SweetDTO sweetDTO) {
        return new Sweet(sweetDTO.getId(), sweetDTO.getColor(), sweetDTO.getGnome());
    }
}
