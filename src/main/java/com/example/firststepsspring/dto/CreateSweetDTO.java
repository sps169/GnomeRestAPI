package com.example.firststepsspring.dto;

import com.example.firststepsspring.model.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSweetDTO {
    private String id;
    private Color color;
    private String gnome_id;
}
