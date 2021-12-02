package com.investimento.controller;

import com.investimento.DAOImpl.ProjetoDAOImpl;
import com.investimento.entity.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoDAOImpl impl;

    @GetMapping
    public ResponseEntity<Iterable<Projeto>> getProjetos() {
        return ResponseEntity.ok(impl.getProjetos());
    }

    @GetMapping("/{id}")
    public ResponseEntity getProjetoById(@PathVariable("id") Long id) {
        Optional<Projeto> projeto = impl.getProjetoById(id);

        if (projeto.isPresent())
            return ResponseEntity.ok(projeto.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}")
    public String insert(@RequestBody Projeto projeto, @PathVariable("id") Long id) {
        Projeto p = impl.insert(projeto, id);

        return "Projeto salvo com sucesso: " + p.getId();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody Projeto projeto) {
        Projeto p = impl.update(projeto, id);

        return "Projeto atualizado com sucesso: " + p.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        impl.delete(id);

        return "Projeto deletado com sucesso";
    }
}
