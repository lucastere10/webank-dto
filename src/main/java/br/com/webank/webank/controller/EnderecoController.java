package br.com.webank.webank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.webank.webank.model.Endereco;
import br.com.webank.webank.service.EnderecoService;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> obterTodos(){
        return ResponseEntity.ok(enderecoService.obterTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<Endereco> adicionar(@RequestBody Endereco endereco){
        Endereco enderecoAdicionado = enderecoService.adicionar(endereco);

        return ResponseEntity
            .status(201)
            .body(enderecoAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco){
        Endereco enderecoAtualizado = enderecoService.atualizar(id, endereco);

        return ResponseEntity
            .status(200)
            .body(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        enderecoService.deletar(id);
        
        return ResponseEntity
            .status(204)
            .build();
    }
}
