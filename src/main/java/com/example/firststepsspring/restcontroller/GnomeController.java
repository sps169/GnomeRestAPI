package com.example.firststepsspring.restcontroller;

import com.example.firststepsspring.dto.GnomeDTO;
import com.example.firststepsspring.dto.mappers.GnomeMapper;
import com.example.firststepsspring.dto.mappers.SweetMapper;
import com.example.firststepsspring.exceptions.gnome.GnomeNotFoundException;
import com.example.firststepsspring.exceptions.ApiError;
import com.example.firststepsspring.model.Color;
import com.example.firststepsspring.model.Gnome;
import com.example.firststepsspring.repository.GnomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GnomeController {

    private final GnomeRepository gnomeRepository;
    private final GnomeMapper gnomeMapper;
    private final SweetMapper sweetMapper;

    //anotacion que permite a la direccion localhost:9001 acceder a todos los verbos crud
    //esta anotacion sobreescribe la configuración global
    @CrossOrigin(origins = "localhost:9001", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping("/gnomes")
    public ResponseEntity<List<GnomeDTO>> getAllGnomes(
            @RequestParam("color") Optional<Color> color
    ) {
        if (color.isPresent())
            return ResponseEntity.ok(gnomeRepository.findAll().stream().map(gnomeMapper::gnomeToDTO).filter(g-> g.getColor().equals(color.get())).collect(Collectors.toList()));
        else
            return ResponseEntity.ok(gnomeRepository.findAll().stream().map(gnomeMapper::gnomeToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/gnomes/{id}")
    public ResponseEntity<GnomeDTO> getGnomesById(@PathVariable("id") String id) throws Exception {
        Gnome gnome = gnomeRepository.findById(id).orElseThrow(() -> new GnomeNotFoundException(id));
        return ResponseEntity.ok(gnomeMapper.gnomeToDTO(gnome));
    }

    @PostMapping("/gnomes")
    public ResponseEntity<GnomeDTO> insertGnome(@RequestBody GnomeDTO gnomeDTO) {
        Gnome gnome = gnomeRepository.save(gnomeMapper.gnomeFromDTO(gnomeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(gnomeMapper.gnomeToDTO(gnome));
    }

    @PutMapping("/gnomes/{id}")
    public ResponseEntity<GnomeDTO> updateGnome(@PathVariable("id") String id, @RequestBody GnomeDTO gnomeDTO) {
        Gnome gnome = gnomeRepository.findById(id).orElseThrow(() -> new GnomeNotFoundException(id));
        gnome.setName(gnomeDTO.getName());
        gnome.setColor(gnomeDTO.getColor());
        gnome.setSweets(gnomeDTO.getSweets().stream().map(sweetMapper::sweetFromDTO).collect(Collectors.toList()));

        gnomeRepository.save(gnome);
        return ResponseEntity.status(HttpStatus.CREATED).body(gnomeMapper.gnomeToDTO(gnome));
    }

    @DeleteMapping("/gnomes/{id}")
    public ResponseEntity<GnomeDTO> deleteGnome(@PathVariable("id") String id) {
        gnomeRepository.findById(id).ifPresent(gnomeRepository::delete);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(GnomeNotFoundException.class)
    public ResponseEntity<ApiError> handleGnomeNotFound(GnomeNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
