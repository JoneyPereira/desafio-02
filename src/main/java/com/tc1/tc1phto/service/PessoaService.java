package com.tc1.tc1phto.service;

import com.tc1.tc1phto.controller.dto.PessoaDTO;
import com.tc1.tc1phto.controller.form.PessoaForm;
import com.tc1.tc1phto.dominio.Pessoa;
import com.tc1.tc1phto.repositorio.RepositorioPessoas;
import com.tc1.tc1phto.service.exceptions.DatabaseException;
import com.tc1.tc1phto.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private RepositorioPessoas pessoasRepositorio;

    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAllPaged(String nome, String sexo, String parentesco, Pageable pageable){
        Page<Pessoa> page = pessoasRepositorio.find(nome, sexo, parentesco, pageable);
        return page.map(x -> new PessoaDTO(x));
    }
    @Transactional
    public PessoaDTO insert(PessoaDTO dto){
        Pessoa entity = new Pessoa();
        copyDtoToEntity(dto, entity);
        entity = pessoasRepositorio.save(entity);
        return new PessoaDTO(entity);
    }
    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id){
        Optional<Pessoa> obj = pessoasRepositorio.findById(id);
        Pessoa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, id: " + id));
        return new PessoaDTO(entity);
    }
    @Transactional
    public PessoaDTO update(Long id, PessoaDTO dto){
        try {
            Pessoa entity = pessoasRepositorio.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = pessoasRepositorio.save(entity);
            return new PessoaDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
    }
    public void delete(Long id){
        try {
            pessoasRepositorio.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }
    private void copyDtoToEntity(PessoaDTO pessoaDto, Pessoa pessoaEntity) {
        pessoaEntity.setNome(pessoaDto.getNome());
        pessoaEntity.setDatanascimento(pessoaDto.getDatanascimento());
        pessoaEntity.setSexo(pessoaDto.getSexo());
        pessoaEntity.setParentesco(pessoaDto.getParentesco());
    }
}
