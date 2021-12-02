package com.investimento.controller;

import com.investimento.DAOImpl.PropostaDAOImpl;
import com.investimento.DAOImpl.UsuarioDAOImpl;
import com.investimento.entity.Proposta;
import com.investimento.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/propostas")
public class PropostaController {

    @Autowired
    private PropostaDAOImpl impl;

    @Autowired
    private UsuarioDAOImpl usuarioImpl;

    @GetMapping
    public ResponseEntity<Iterable<Proposta>> getPropostas() {
        return ResponseEntity.ok(impl.getPropostas());
    }

    @GetMapping("/{id}")
    public ResponseEntity getPropostaById(@PathVariable("id") Long id) {
        Optional<Proposta> proposta = impl.getPropostaById(id);

        if (proposta.isPresent())
            return ResponseEntity.ok(proposta.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}")
    public String insert(@RequestBody Proposta proposta, @PathVariable("id") Long id) {
        Proposta p = impl.insert(proposta, id);

        return "Proposta salva com sucesso: " + p.getId();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody Proposta proposta) {
        Proposta p = impl.update(proposta, id);

        return "Proposta atualizada com sucesso: " + p.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        impl.delete(id);

        return "Proposta deletada com sucesso";
    }

    @GetMapping("/investir/{idProposta}/usuario/{idUsuario}")
    public String investir(@PathVariable("idProposta") Long idProposta, @PathVariable("idUsuario") Long idUsuario) {
        Optional<Usuario> usuario = usuarioImpl.getUsuarioById(idUsuario);
        Optional<Proposta> proposta = impl.getPropostaById(idProposta);

        if (usuario.get().getEInvestidor() == false) {
            return "Você ainda não pode investir, registre-se como um investidor.";
        } else {
            if (proposta.get().getStatus().getId() == 3 || proposta.get().getStatus().getId() == 2) {
                return "Você não pode mais contribuir com essa proposta";
            } else {
                return "Obrigado! Você investiu R$" + proposta.get().getValor() + " nesse projeto!";
            }
        }
    }

}