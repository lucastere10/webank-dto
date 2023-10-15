package br.com.webank.webank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webank.webank.model.Endereco;
import br.com.webank.webank.repository.EnderecoRepository;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> obterTodos(){
        return enderecoRepository.findAll();
    }

    public Endereco obterPorId(long id){
        Optional<Endereco> optEndereco = enderecoRepository.findById(id);

        if(optEndereco.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optEndereco.get();
    }

    // O save serve tanto para adicionar quanto para atualizar.
    // se tiver id, ele atualiza, s enão tiver id ele adiciona.
    public Endereco adicionar(Endereco endereco){
        endereco.setId(0);
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(long id, Endereco endereco){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        endereco.setId(id);
        return enderecoRepository.save(endereco);
    }

    public void deletar(Long id){
        obterPorId(id);

        enderecoRepository.deleteById(id);
    }

}
