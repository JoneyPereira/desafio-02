package com.tc1.tc1phto.service;

import com.tc1.tc1phto.controller.dto.CasaDTO;
import com.tc1.tc1phto.dominio.Casa;
import com.tc1.tc1phto.repositorio.RepositorioCasas;
import com.tc1.tc1phto.service.exceptions.DatabaseException;
import com.tc1.tc1phto.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CasaService {
    @Autowired
    private RepositorioCasas casasRepositorio;

//    @Transactional(readOnly = true)
//    public Page<CasaDTO> findAllPaged(String pessoa, String cep, Pageable pageable){
//        Page<Casa> page = casasRepositorio.find(pessoa, cep, pageable);
//        return page.map(x -> new CasaDTO(x));
//    }
    @Transactional
    public CasaDTO insert(CasaDTO dto){
        Casa entity = new Casa();
        copyDtoToEntity(dto, entity);
        entity = casasRepositorio.save(entity);
        return new CasaDTO(entity);
    }
    @Transactional(readOnly = true)
    public CasaDTO findById(Long id){
        Optional<Casa> obj = casasRepositorio.findById(id);
        Casa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, id: " + id));
        return new CasaDTO(entity);
    }
    @Transactional
    public CasaDTO update(Long id, CasaDTO dto){
        try {
            Casa entity = casasRepositorio.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = casasRepositorio.save(entity);
            return new CasaDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
    }
    public void delete(Long id){
        try {
            casasRepositorio.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }
    private void copyDtoToEntity(CasaDTO casaDto, Casa casaEntity) {
        casaEntity.setId(casaDto.getId());
    }
}
