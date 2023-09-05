package com.tc1.tc1phto.repositorio;

import com.tc1.tc1phto.dominio.Eletrodomestico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEletrodomesticos extends JpaRepository<Eletrodomestico, Long> {

    @Query("SELECT DISTINCT obj FROM Eletrodomestico obj "
            + "WHERE (LOWER(obj.nome) LIKE LOWER(CONCAT('%',:nome,'%'))) "
            + "AND (LOWER(obj.modelo) LIKE LOWER(CONCAT('%',:modelo,'%'))) "
            + "AND (LOWER(obj.potencia) LIKE LOWER(CONCAT('%',:potencia,'%'))) ")
    Page<Eletrodomestico> find(String nome, String modelo, String potencia, Pageable pageable);
}
