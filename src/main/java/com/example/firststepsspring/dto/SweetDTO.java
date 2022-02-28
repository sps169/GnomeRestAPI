package com.example.firststepsspring.dto;

import com.example.firststepsspring.model.Color;
import com.example.firststepsspring.model.Gnome;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SweetDTO {
    private String id;
    private Color color;
    private String gnomeId;
    @JsonIgnore
    private Gnome gnome;
}
