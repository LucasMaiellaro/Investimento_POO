package com.investimento.controller;

import com.investimento.DAOImpl.UsuarioDAOImpl;
import com.investimento.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDAOImpl impl;

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> getUsuarios() {
        return ResponseEntity.ok(impl.getUsuarios());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Optional<Usuario>> getUsuarioByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(impl.getUsuarioByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUsuarioById(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = impl.getUsuarioById(id);

        if (usuario.isPresent())
            return ResponseEntity.ok(usuario.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public String insert(@RequestBody Usuario usuario) {
        Usuario u = impl.insert(usuario);

        return "Usuario salvo com sucesso: " + u.getId();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Usuario u = impl.update(usuario, id);

        return "Usuario atualizado com sucesso: " + u.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        impl.delete(id);

        return "Usuario deletado com sucesso";
    }

}