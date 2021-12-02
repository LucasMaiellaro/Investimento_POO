package com.investimento.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Status {

    @Id
    private Long id;
    private String descricao;

}
