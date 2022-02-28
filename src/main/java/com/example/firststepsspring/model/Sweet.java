package com.example.firststepsspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sweet {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Color color;
    @ManyToOne
    private Gnome gnome;
}
