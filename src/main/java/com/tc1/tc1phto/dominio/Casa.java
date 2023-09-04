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
    @Column(name = "uuid")
    private String casa_uuid;

    @OneToMany(mappedBy = "id")
    private List<Pessoa> pessoas = new ArrayList<>();

    @OneToOne(mappedBy = "id")
    private Endereco endereco;

    @OneToMany(mappedBy = "id")
    private List<Eletrodomestico> eletrodomesticos = new ArrayList<>();
    public Casa(String casa_uuid){
        this.casa_uuid = casa_uuid;
    }
}
