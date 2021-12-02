package com.investimento.DAO;

import com.investimento.entity.Projeto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjetoRepository extends CrudRepository<Projeto, Long> {
}
