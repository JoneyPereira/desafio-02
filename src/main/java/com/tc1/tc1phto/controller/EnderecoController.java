package com.tc1.tc1phto.controller;

import com.tc1.tc1phto.controller.dto.EnderecoDTO;
import com.tc1.tc1phto.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    @GetMapping
    public ResponseEntity<Page<EnderecoDTO>> findAll(
            @RequestParam(value = "rua", defaultValue = "") String rua,
            @RequestParam(value = "bairro", defaultValue = "") String bairro,
            @RequestParam(value = "cep", defaultValue = "") String cep,
            Pageable pageable)
    {
        Page<EnderecoDTO> page = enderecoService.findAllPaged(rua.trim(), bairro.trim(), cep.trim(), pageable);
        return ResponseEntity.ok().body(page);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id){

        EnderecoDTO dto = enderecoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<EnderecoDTO> insert(@Valid @RequestBody EnderecoDTO enderecoDto){

        enderecoDto = enderecoService.insert(enderecoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @Valid @RequestBody EnderecoDTO dto){

        dto = enderecoService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
