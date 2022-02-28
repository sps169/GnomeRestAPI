package com.example.firststepsspring.dto;

import com.example.firststepsspring.model.Color;
import com.example.firststepsspring.model.Sweet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GnomeDTO {
    private String id;
    private String name;
    private Color color;
    private List<SweetDTO> sweets;
}
