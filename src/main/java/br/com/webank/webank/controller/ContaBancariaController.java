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

import br.com.webank.webank.model.ContaBancaria;
import br.com.webank.webank.service.ContaBancariaService;

@RestController
@RequestMapping("/api/contas-bancarias")
public class ContaBancariaController {
    
    @Autowired
    private ContaBancariaService contaBancariaService;

    @GetMapping
    public ResponseEntity<List<ContaBancaria>> obterTodos(){
        return ResponseEntity.ok(contaBancariaService.obterTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(contaBancariaService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContaBancaria> adicionar(@RequestBody ContaBancaria contaBancaria){
        ContaBancaria contaBancariaAdicionado = contaBancariaService.adicionar(contaBancaria);

        return ResponseEntity
            .status(201)
            .body(contaBancariaAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> atualizar(@PathVariable Long id, @RequestBody ContaBancaria contaBancaria){
        ContaBancaria contaBancariaAtualizado = contaBancariaService.atualizar(id, contaBancaria);

        return ResponseEntity
            .status(200)
            .body(contaBancariaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        contaBancariaService.deletar(id);
        
        return ResponseEntity
            .status(204)
            .build();
    }
}
