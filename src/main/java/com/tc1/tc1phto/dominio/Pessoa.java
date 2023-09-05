package com.tc1.tc1phto.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@Table(name="tb_pessoa")
public class Pessoa {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Long id;

    @JsonProperty
    @Column(name="nome")
    private String nome;


    @JsonProperty
    @Column(name="data_nascimento")
    private String datanascimento;


    @JsonProperty
    @Column(name="sexo")
    private String sexo;

    @JsonProperty
    @Column(name="parentesco")
    private String parentesco;

    @ManyToMany
    @JoinColumn(name = "endereco_id")
    private Set<Endereco> enderecos = new HashSet<>();

    @ManyToMany
    @JoinColumn(name = "casa_id")
    private Set<Casa> casas = new HashSet<>();

    @ManyToMany
    @JoinColumn(name = "eletromestico_id")
    private Set<Eletrodomestico> eletromesticos = new HashSet<>();

    public Pessoa (String nome, String datanascimento, String sexo, String parentesco){
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.sexo = sexo;
        this.parentesco = parentesco;
    }
}
