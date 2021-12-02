package com.investimento.DAO;

import com.investimento.entity.Proposta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {
}
