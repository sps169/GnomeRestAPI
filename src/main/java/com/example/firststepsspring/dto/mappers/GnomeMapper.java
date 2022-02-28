package com.example.firststepsspring.dto.mappers;


import com.example.firststepsspring.dto.GnomeDTO;
import com.example.firststepsspring.model.Gnome;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GnomeMapper {

    private final SweetMapper sweetMapper;

    public GnomeMapper(SweetMapper sweetMapper) {
        this.sweetMapper = sweetMapper;
    }

    //forma builder
    public GnomeDTO gnomeToDTO(Gnome gnome) {
        return GnomeDTO.builder()
                .id(gnome.getId())
                .name(gnome.getName())
                .color(gnome.getColor())
                .sweets(gnome.getSweets().stream().map(sweetMapper::sweetToDTO).collect(Collectors.toList()))
                .build();
    }

    //forma no builder
    public Gnome gnomeFromDTO(GnomeDTO gnomeDTO) {
        return new Gnome(gnomeDTO.getId(), gnomeDTO.getName(), gnomeDTO.getColor(), gnomeDTO.getSweets().stream().map(sweetMapper::sweetFromDTO).collect(Collectors.toList()));
    }
}
