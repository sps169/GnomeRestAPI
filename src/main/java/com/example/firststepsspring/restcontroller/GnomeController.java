package com.example.firststepsspring.restcontroller;

import com.example.firststepsspring.dto.GnomeDTO;
import com.example.firststepsspring.dto.mappers.GnomeMapper;
import com.example.firststepsspring.model.Color;
import com.example.firststepsspring.model.Gnome;
import com.example.firststepsspring.repository.GnomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GnomeController {

    private final GnomeRepository gnomeRepository;
    private final GnomeMapper gnomeMapper;

    @GetMapping("/gnomes")
    public ResponseEntity<List<GnomeDTO>> getAllGnomes(@RequestParam("color") Optional<Color> color) {
        if (color.isPresent())
            return ResponseEntity.ok(gnomeRepository.findAll().stream().map(gnomeMapper::gnomeToDTO).filter(g-> g.getColor().equals(color.get())).collect(Collectors.toList()));
        else
            return ResponseEntity.ok(gnomeRepository.findAll().stream().map(gnomeMapper::gnomeToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/gnomes/{id}")
    public ResponseEntity<GnomeDTO> getGnomesById(@PathVariable("id") String id) throws Exception {
        Optional<Gnome> gnome = gnomeRepository.findById(id);
        if (gnome.isPresent()) {
            return ResponseEntity.ok(gnomeMapper.gnomeToDTO(gnome.get()));
        }else {
            throw new Exception("Gnome not found");
        }
    }

    @PostMapping("/gnomes")
    public ResponseEntity<GnomeDTO> insertGnome(@RequestBody GnomeDTO gnomeDTO) {
        Gnome gnome = gnomeRepository.save(gnomeMapper.gnomeFromDTO(gnomeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(gnomeMapper.gnomeToDTO(gnome));
    }

    @DeleteMapping("/gnomes/{id}")
    public ResponseEntity<GnomeDTO> deleteGnome(@PathVariable("id") String id) {
        gnomeRepository.findById(id).ifPresent(gnomeRepository::delete);
        return ResponseEntity.noContent().build();
    }
}
