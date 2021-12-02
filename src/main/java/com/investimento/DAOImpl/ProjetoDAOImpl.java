package com.investimento.DAOImpl;

import com.investimento.DAO.ProjetoRepository;
import com.investimento.entity.Projeto;
import com.investimento.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ProjetoDAOImpl {

    @Autowired
    private ProjetoRepository dao;

    @Autowired
    private UsuarioDAOImpl usuarioImpl;

    public Iterable<Projeto> getProjetos() {
        return dao.findAll();
    }

    public Optional<Projeto> getProjetoById(Long id) {
        return dao.findById(id);
    }

    public Projeto insert(Projeto projeto, Long id) {
        Assert.notNull(id, "Não foi possível salvar o registro.");

        Optional<Usuario> usuario = usuarioImpl.getUsuarioById(id);
        usuario.get().getProjetos().add(projeto);
        return dao.save(projeto);
    }

    public Projeto update(Projeto projeto, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro.");

        Optional<Projeto> optional = getProjetoById(id);

        if (optional.isPresent()) {
            Projeto db = optional.get();

            db.setNome(projeto.getNome());
            db.setDescricao(projeto.getDescricao());
            dao.save(db);
            return db;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro.");
        }
    }

    public void delete(Long id) {
        Optional<Projeto> projeto = getProjetoById(id);
        if (projeto.isPresent())
            dao.deleteById(id);
    }

}
