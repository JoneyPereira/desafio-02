package com.tc1.tc1phto.repositorio;

import com.tc1.tc1phto.dominio.Endereco;
import com.tc1.tc1phto.dominio.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
 public interface RepositorioPessoas extends JpaRepository<Pessoa, Long> {
  @Query("SELECT DISTINCT obj FROM Pessoa obj "
          + "WHERE (LOWER(obj.nome) LIKE LOWER(CONCAT('%',:nome,'%'))) "
          + "AND (LOWER(obj.sexo) LIKE LOWER(CONCAT('%',:sexo,'%'))) "
          + "AND (LOWER(obj.parentesco) LIKE LOWER(CONCAT('%',:parentesco,'%'))) ")
  Page<Pessoa> find(String nome, String sexo, String parentesco, Pageable pageable);
}
