package com.tc1.tc1phto.controller.dto;

import com.tc1.tc1phto.dominio.Pessoa;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class PessoaDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Nome não pode estar em branco e não pode ser nulo.")
    private String nome;

    @NotBlank(message = "Data de Nascimento não pode estar em branco e não pode ser nulo.")
    private String datanascimento;

    @NotBlank(message = "Sexo não pode estar em branco e não pode ser nulo.")
    private String sexo;

    @NotBlank(message = "Parentesco não pode estar em branco e não pode ser nulo.")
    private String parentesco;

    public PessoaDTO(Pessoa entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.datanascimento = entity.getDatanascimento();
        this.sexo = entity.getSexo();
        this.parentesco = entity.getParentesco();
    }
}
