package com.example.firststepsspring.repository;

import com.example.firststepsspring.model.Gnome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GnomeRepository extends JpaRepository<Gnome, String> {
}
