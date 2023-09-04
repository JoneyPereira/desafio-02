package com.tc1.tc1phto.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Data
@Entity
@NoArgsConstructor
@Table(name="tb_endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    private Long id;

    @JsonProperty
    @Column(name="rua")
    private String rua;

    @JsonProperty
    @Column(name="numero")
    private String numero;

    @JsonProperty
    @Column(name="bairro")
    private String bairro;

    @JsonProperty
    @Column(name="cidade")
    private String cidade;

    @JsonProperty
    @Column(name="cep")
    private String cep;

    @JsonProperty
    @Column(name="estado")
    private String estado;

    @OneToOne
    @JoinColumn(name = "id")
    private Casa casa;

    @ManyToMany
    @JoinTable(name = "tb_endereco_eletrodomestico", joinColumns = @JoinColumn(name = "endereco_id"), inverseJoinColumns = @JoinColumn(name = "eletromestico_id"))
    private Set<Eletrodomestico> eletromesticos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_endereco_pessoa", joinColumns = @JoinColumn(name = "endereco_id"), inverseJoinColumns = @JoinColumn(name = "pessoa_id"))
    private Set<Pessoa> pessoas = new HashSet<>();
    public Endereco(String rua, String numero, String bairro, String cidade,String cep, String estado){
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.estado = estado;
    }
}
