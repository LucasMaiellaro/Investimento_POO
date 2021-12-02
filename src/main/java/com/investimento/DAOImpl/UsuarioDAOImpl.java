package com.investimento.DAOImpl;

import com.investimento.DAO.UsuarioRepository;
import com.investimento.entity.Proposta;
import com.investimento.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioDAOImpl {

    @Autowired
    private UsuarioRepository dao;

    public Iterable<Usuario> getUsuarios() {
        return dao.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return dao.findById(id);
    }

    public Usuario insert(Usuario usuario) {
        return dao.save(usuario);
    }

    public Usuario update(Usuario usuario, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro.");

        Optional<Usuario> optional = getUsuarioById(id);

        if (optional.isPresent()) {
            Usuario db = optional.get();

            db.setNome(usuario.getNome());
            db.setEmail(usuario.getEmail());
            db.setSenha(usuario.getSenha());
            db.setEInvestidor(usuario.getEInvestidor());
            dao.save(db);
            return db;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro.");
        }
    }

    public void delete(Long id) {
        Optional<Usuario> usuario = getUsuarioById(id);
        if (usuario.isPresent())
            dao.deleteById(id);
    }

}