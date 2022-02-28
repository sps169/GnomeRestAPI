package com.example.firststepsspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gnome {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Color color;
    @OneToMany(mappedBy = "gnome")
    private List<Sweet> sweets;
}
