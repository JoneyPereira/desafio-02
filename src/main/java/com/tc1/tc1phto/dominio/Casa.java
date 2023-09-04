package com.tc1.tc1phto.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name="tb_casa")
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "casa_id")
    private String id;

    @OneToMany
    @JoinColumn(name = "pessoa_id")
    private List<Pessoa> pessoas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany
    @JoinColumn(name = "eletrodomestico_id")
    private List<Eletrodomestico> eletrodomesticos = new ArrayList<>();
    public Casa(String id){
        this.id = id;
    }
}
