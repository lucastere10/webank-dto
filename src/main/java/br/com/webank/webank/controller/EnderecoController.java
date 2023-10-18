package br.com.webank.webank.controller;

import java.util.ArrayList;
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

import br.com.webank.webank.dto.endereco.EnderecoRequestDTO;
import br.com.webank.webank.dto.endereco.EnderecoResponseDTO;
import br.com.webank.webank.model.Endereco;
import br.com.webank.webank.model.email.Email;
import br.com.webank.webank.service.EmailService;
import br.com.webank.webank.service.EnderecoService;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> obterTodos(){
        return ResponseEntity.ok(enderecoService.obterTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> adicionar(@RequestBody EnderecoRequestDTO endereco){
        EnderecoResponseDTO enderecoAdicionado = enderecoService.adicionar(endereco);

        return ResponseEntity
            .status(201)
            .body(enderecoAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable Long id, @RequestBody EnderecoRequestDTO endereco){
        EnderecoResponseDTO enderecoAtualizado = enderecoService.atualizar(id, endereco);

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

    @GetMapping("/email")
    public ResponseEntity<?> testeEnvioDeEmail(){

        List<String> destinatarios = new ArrayList<>();
        destinatarios.add("emaildestinatario");


        String mensagem = "<h1 style=\"text-align: center;\">Pedido N&deg; 562</h1>\r\n" + //
                "<p style=\"text-align: center;\"><br>Caro cliente Jose Francisco, sua compra foi aprovada! 😁</p>\r\n" + //
                "<p style=\"text-align: center;\"><img src=\"blob:https://www.tiny.cloud/e635d795-cbe0-44cd-b71c-83e621694819\"></p>";

        Email email = new Email("Teste de email", mensagem, "weberson.r.a@gmail.com", destinatarios);

        emailService.enviar(email);

        return ResponseEntity.status(200).body("E-mail enviado com sucesso!!!");
    }
}
