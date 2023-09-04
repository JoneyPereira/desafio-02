package com.tc1.tc1phto.repositorio;

import com.tc1.tc1phto.dominio.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 public interface RepositorioCasas extends JpaRepository<Casa, Long> {
//  @Query("SELECT DISTINCT obj FROM Pessoa obj "
//          + "WHERE (LOWER(obj.pessoa) LIKE LOWER(CONCAT('%',:pessoa,'%'))) "
//          + "AND (LOWER(obj.cep) LIKE LOWER(CONCAT('%',:cep,'%'))) ")
//  Page<Casa> find(String pessoa, String cep, Pageable pageable);
}
