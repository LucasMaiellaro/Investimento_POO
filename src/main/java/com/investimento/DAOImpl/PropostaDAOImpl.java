package com.investimento.DAOImpl;

import com.investimento.DAO.PropostaRepository;
import com.investimento.entity.Projeto;
import com.investimento.entity.Proposta;
import com.investimento.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class PropostaDAOImpl {

    @Autowired
    private PropostaRepository dao;

    @Autowired
    private ProjetoDAOImpl projetoImpl;

    public Iterable<Proposta> getPropostas() {
        return dao.findAll();
    }

    public Optional<Proposta> getPropostaById(Long id) {
        return dao.findById(id);
    }

    public Proposta insert(Proposta proposta, Long id) {
        Assert.notNull(id, "Não foi possível salvar o registro.");

        Optional<Projeto> projeto = projetoImpl.getProjetoById(id);
        projeto.get().getPropostas().add(proposta);
        return dao.save(proposta);
    }

    public Proposta update(Proposta proposta, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro.");

        Optional<Proposta> optional = getPropostaById(id);

        if (optional.isPresent()) {
            Proposta db = optional.get();

            db.setValor(proposta.getValor());
            db.setPorcentagem(proposta.getPorcentagem());

            dao.save(db);
            return db;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro.");
        }
    }

    public void delete(Long id) {
        Optional<Proposta> proposta = getPropostaById(id);
        if (proposta.isPresent())
            proposta.get().getStatus().setId(2L);
    }

}
