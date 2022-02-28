package com.example.firststepsspring.restcontroller;

import com.example.firststepsspring.dto.SweetDTO;
import com.example.firststepsspring.dto.mappers.SweetMapper;
import com.example.firststepsspring.model.Sweet;
import com.example.firststepsspring.repository.SweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SweetController {
    private final SweetRepository sweetRepository;
    private final SweetMapper sweetMapper;

    @GetMapping("/sweets")
    public ResponseEntity<List<SweetDTO>> findAllSweets() {
        return ResponseEntity.ok().body(sweetRepository.findAll().stream().map(sweetMapper::sweetToDTO).collect(Collectors.toList()));
    }

    @PostMapping("/sweets")
    public ResponseEntity<SweetDTO> insertSweet(@RequestBody SweetDTO sweetDTO) {
        Sweet sweet = sweetRepository.save(sweetMapper.sweetFromDTO(sweetDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(sweetMapper.sweetToDTO(sweet));
    }

    @DeleteMapping("/sweets/{id}")
    public ResponseEntity<SweetDTO> deleteSweet(@PathVariable("id") String id) {
        Optional<Sweet> sweet = sweetRepository.findById(id);
        if (sweet.isPresent()) {
            sweetRepository.delete(sweet.get());
            return ResponseEntity.ok(sweetMapper.sweetToDTO(sweet.get()));
        }else {
            return ResponseEntity.notFound().build();
        }

    }
}
