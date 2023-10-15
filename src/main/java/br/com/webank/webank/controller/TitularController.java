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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.webank.webank.model.Titular;
import br.com.webank.webank.service.TitularService;

@RestController
@RequestMapping("/api/titulares")
public class TitularController {
    
    @Autowired
    private TitularService titularService;

    @GetMapping
    public ResponseEntity<List<Titular>> obterTodos(){
        return ResponseEntity.ok(titularService.obterTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Titular> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(titularService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<Titular> adicionar(@RequestBody Titular titular){
        Titular titularAdicionado = titularService.adicionar(titular);

        return ResponseEntity
            .status(201)
            .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Titular> atualizar(@PathVariable Long id, @RequestBody Titular titular){
        Titular titularAtualizado = titularService.atualizar(id, titular);

        return ResponseEntity
            .status(200)
            .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        titularService.deletar(id);
        
        return ResponseEntity
            .status(204)
            .build();
    }
}
