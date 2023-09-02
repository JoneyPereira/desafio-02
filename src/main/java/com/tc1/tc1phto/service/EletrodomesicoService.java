package com.tc1.tc1phto.service;

import com.tc1.tc1phto.controller.dto.EletrodomesticoDTO;
import com.tc1.tc1phto.dominio.Eletrodomestico;
import com.tc1.tc1phto.repositorio.RepositorioEletrodomesticos;
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
public class EletrodomesicoService {
    @Autowired
    private RepositorioEletrodomesticos eletrodomesticosRepositorio;

    @Transactional(readOnly = true)
    public Page<EletrodomesticoDTO> findAllPaged(String nome, String modelo, String potencia, Pageable pageable){
        Page<Eletrodomestico> page = eletrodomesticosRepositorio.find(nome, modelo, potencia, pageable);
        return page.map(x -> new EletrodomesticoDTO(x));
    }
    @Transactional
    public EletrodomesticoDTO insert(EletrodomesticoDTO dto){
        Eletrodomestico entity = new Eletrodomestico();
        copyDtoToEntity(dto, entity);
        entity = eletrodomesticosRepositorio.save(entity);
        return new EletrodomesticoDTO(entity);
    }
    @Transactional(readOnly = true)
    public EletrodomesticoDTO findById(Long id){
        Optional<Eletrodomestico> obj = eletrodomesticosRepositorio.findById(id);
        Eletrodomestico entity = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, id: " + id));
        return new EletrodomesticoDTO(entity);
    }

    @Transactional
    public EletrodomesticoDTO update(Long id, EletrodomesticoDTO dto){
        try {
            Eletrodomestico entity = eletrodomesticosRepositorio.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = eletrodomesticosRepositorio.save(entity);
            return new EletrodomesticoDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
    }

    public void delete(Long id){
        try {
            eletrodomesticosRepositorio.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }
    private void copyDtoToEntity(EletrodomesticoDTO eletrodomesticoDto, Eletrodomestico eletrodomesticoEntity) {
        eletrodomesticoEntity.setNome(eletrodomesticoDto.getNome());
        eletrodomesticoEntity.setModelo(eletrodomesticoDto.getModelo());
        eletrodomesticoEntity.setPotencia(eletrodomesticoDto.getPotencia());
        eletrodomesticoEntity.setSelo(eletrodomesticoDto.getSelo());
    }
}
