package com.tc1.tc1phto.service;

import com.tc1.tc1phto.controller.dto.EnderecoDTO;
import com.tc1.tc1phto.dominio.Endereco;
import com.tc1.tc1phto.repositorio.RepositorioEnderecos;
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
public class EnderecoService {
    @Autowired
    private RepositorioEnderecos enderecosRepositorio;

    @Transactional(readOnly = true)
    public Page<EnderecoDTO> findAllPaged(String rua, String bairro, String cep, Pageable pageable){
        Page<Endereco> page = enderecosRepositorio.find(rua, bairro, cep, pageable);
        return page.map(x -> new EnderecoDTO(x));
    }
    @Transactional
    public EnderecoDTO insert(EnderecoDTO dto){
        Endereco entity = new Endereco();
        copyDtoToEntity(dto, entity);
        entity = enderecosRepositorio.save(entity);
        return new EnderecoDTO(entity);
    }
    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id){
        Optional<Endereco> obj = enderecosRepositorio.findById(id);
        Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, id: " + id));
        return new EnderecoDTO(entity);
    }
    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto){
        try {
            Endereco entity = enderecosRepositorio.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = enderecosRepositorio.save(entity);
            return new EnderecoDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
    }
    public void delete(Long id){
        try {
            enderecosRepositorio.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }
    private void copyDtoToEntity(EnderecoDTO enderecoDto, Endereco enderecoEntity) {
        enderecoEntity.setRua(enderecoDto.getRua());
        enderecoEntity.setNumero(enderecoDto.getNumero());
        enderecoEntity.setBairro(enderecoDto.getBairro());
        enderecoEntity.setCidade(enderecoDto.getCidade());
        enderecoEntity.setCep(enderecoDto.getCep());
        enderecoEntity.setEstado(enderecoDto.getEstado());
    }
}
