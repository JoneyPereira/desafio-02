package com.tc1.tc1phto.repositorio;

import com.tc1.tc1phto.dominio.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEnderecos extends JpaRepository<Endereco, Long> {
    @Query("SELECT DISTINCT obj FROM Endereco obj "
            + "WHERE (LOWER(obj.rua) LIKE LOWER(CONCAT('%',:rua,'%'))) "
            + "AND (LOWER(obj.bairro) LIKE LOWER(CONCAT('%',:bairro,'%'))) "
            + "AND (LOWER(obj.cep) LIKE LOWER(CONCAT('%',:cep,'%'))) ")
    Page<Endereco> find(String rua, String bairro, String cep, Pageable pageable);
}
