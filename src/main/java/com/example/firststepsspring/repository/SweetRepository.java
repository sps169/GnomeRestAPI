package com.example.firststepsspring.repository;

import com.example.firststepsspring.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SweetRepository extends JpaRepository<Sweet, String> {

}
