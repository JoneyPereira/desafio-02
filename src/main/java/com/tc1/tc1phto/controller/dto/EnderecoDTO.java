package com.tc1.tc1phto.controller.dto;

import com.tc1.tc1phto.dominio.Endereco;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class EnderecoDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Nome não pode estar em branco e não pode ser nulo.")
    private String rua;

    @NotBlank(message = "Numero não pode estar em branco e não pode ser nulo.")
    private String numero;

    @NotBlank(message = "Bairro não pode estar em branco e não pode ser nulo.")
    private String bairro;

    @NotBlank(message = "Cidade não pode estar em branco e não pode ser nulo.")
    private String cidade;

    @NotBlank(message = "Cep não pode estar em branco e não pode ser nulo.")
    private String cep;

    @NotBlank(message = "Estado não pode estar em branco e não pode ser nulo.")
    private String estado;

    public EnderecoDTO(Endereco entity){
        this.id = entity.getId();
        this.rua = entity.getRua();
        this.numero = entity.getNumero();
        this.bairro = entity.getBairro();
        this.cidade = entity.getCidade();
        this.cep = entity.getCep();
        this.estado = entity.getEstado();
    }
}
