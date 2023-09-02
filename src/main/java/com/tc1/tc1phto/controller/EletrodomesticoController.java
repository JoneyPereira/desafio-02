package com.tc1.tc1phto.controller;

import com.tc1.tc1phto.controller.dto.EletrodomesticoDTO;
import com.tc1.tc1phto.service.EletrodomesicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.Validator;
import java.net.URI;

@RestController
@RequestMapping("/eletrodomesticos")
public class EletrodomesticoController {
    @Autowired
    private Validator validator;
    @Autowired
    private EletrodomesicoService eletrodomesicoService;

    @GetMapping()
    public ResponseEntity<Page<EletrodomesticoDTO>> findAllPaged(Pageable pageable){

        Page<EletrodomesticoDTO> list = eletrodomesicoService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<EletrodomesticoDTO> findById(@PathVariable Long id){

        EletrodomesticoDTO dto = eletrodomesicoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<EletrodomesticoDTO> insert(@Valid @RequestBody EletrodomesticoDTO eletrodomesticoDto){

        eletrodomesticoDto = eletrodomesicoService.insert(eletrodomesticoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eletrodomesticoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(eletrodomesticoDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<EletrodomesticoDTO> update(@PathVariable Long id, @Valid @RequestBody EletrodomesticoDTO dto){

        dto = eletrodomesicoService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){

        eletrodomesicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
