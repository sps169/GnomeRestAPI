package com.example.firststepsspring.restcontroller;

import com.example.firststepsspring.dto.CreateSweetDTO;
import com.example.firststepsspring.dto.SweetDTO;
import com.example.firststepsspring.dto.mappers.CreateSweetMapper;
import com.example.firststepsspring.dto.mappers.GnomeMapper;
import com.example.firststepsspring.dto.mappers.SweetMapper;
import com.example.firststepsspring.exceptions.ApiError;
import com.example.firststepsspring.exceptions.gnome.GnomeNotFoundException;
import com.example.firststepsspring.exceptions.sweet.SweetNotFoundException;
import com.example.firststepsspring.model.Gnome;
import com.example.firststepsspring.model.Sweet;
import com.example.firststepsspring.repository.GnomeRepository;
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
    private final GnomeRepository gnomeRepository;
    private final SweetMapper sweetMapper;
    private final CreateSweetMapper createSweetMapper;
    private final GnomeMapper gnomeMapper;

    @GetMapping("/sweets")
    public ResponseEntity<List<SweetDTO>> getAllSweets() {
        return ResponseEntity.ok().body(sweetRepository.findAll().stream().map(sweetMapper::sweetToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/sweets/{id}")
    public ResponseEntity<SweetDTO> getSweetById(@PathVariable("id") String id) {
        Sweet sweet = sweetRepository.findById(id).orElseThrow(() -> new SweetNotFoundException(id));
        return ResponseEntity.ok(sweetMapper.sweetToDTO(sweet));
    }

    @PostMapping("/sweets")
    public ResponseEntity<SweetDTO> insertSweet(@RequestBody CreateSweetDTO createSweetDTO) {
        Sweet sweet = createSweetMapper.fromDTO(createSweetDTO);
        sweet.setGnome(gnomeRepository.findById(createSweetDTO.getGnome_id()).orElseThrow(() -> new GnomeNotFoundException(createSweetDTO.getGnome_id())));
        sweet = sweetRepository.save(sweet);
        return ResponseEntity.status(HttpStatus.CREATED).body(sweetMapper.sweetToDTO(sweet));
    }

    @PutMapping("/sweets/{id}")
    public ResponseEntity<SweetDTO> updateSweet(@PathVariable("id") String id, @RequestBody CreateSweetDTO createSweetDTO){
        Sweet sweet = sweetRepository.findById(id).orElseThrow(() -> new SweetNotFoundException(id));
        sweet.setColor(createSweetDTO.getColor());
        sweet.setGnome(gnomeRepository.findById(createSweetDTO.getGnome_id()).orElseThrow(() -> new GnomeNotFoundException(createSweetDTO.getGnome_id())));
        return ResponseEntity.status(HttpStatus.CREATED).body(sweetMapper.sweetToDTO(sweetRepository.save(sweet)));
    }
    @DeleteMapping("/sweets/{id}")
    public ResponseEntity<SweetDTO> deleteSweet(@PathVariable("id") String id) {
        Sweet sweet = sweetRepository.findById(id).orElseThrow(() -> new SweetNotFoundException(id));
        sweetRepository.delete(sweet);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(SweetNotFoundException.class)
    public ResponseEntity<ApiError> handleSweetNotFound(SweetNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
