package com.tc1.tc1phto.controller.dto;

import com.tc1.tc1phto.dominio.Casa;
import com.tc1.tc1phto.dominio.Eletrodomestico;
import com.tc1.tc1phto.dominio.Endereco;
import com.tc1.tc1phto.dominio.Pessoa;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class CasaDTO {
    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank(message = "Pessoa não pode estar em branco e não pode ser nulo.")
    private Pessoa pessoa;

    @NotBlank(message = "Endereco de Nascimento não pode estar em branco e não pode ser nulo.")
    private Endereco endereco;

    @NotBlank(message = "Eletrodomestico não pode estar em branco e não pode ser nulo.")
    private Eletrodomestico eletrodomestico;

    public CasaDTO(Casa entity){
        this.id = entity.getId();
    }
}
