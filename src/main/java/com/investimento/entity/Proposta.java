package com.investimento.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private Double porcentagem;

}
