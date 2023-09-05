package com.tc1.tc1phto.controller;

import com.tc1.tc1phto.controller.dto.PessoaDTO;
import com.tc1.tc1phto.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "sexo", defaultValue = "") String sexo,
            @RequestParam(value = "parentesco", defaultValue = "") String parentesco,
            Pageable pageable)
    {
        Page<PessoaDTO> page = pessoaService.findAllPaged(nome.trim(), sexo.trim(), parentesco.trim(), pageable);
        return ResponseEntity.ok().body(page);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id){

        PessoaDTO dto = pessoaService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<PessoaDTO> insert(@Valid @RequestBody PessoaDTO pessoaDto){

        pessoaDto = pessoaService.insert(pessoaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaDto.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaDTO dto){

        dto = pessoaService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
