package br.com.webank.webank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webank.webank.model.ContaBancaria;
import br.com.webank.webank.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
    
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public List<ContaBancaria> obterTodos(){
        return contaBancariaRepository.findAll();
    }

    public ContaBancaria obterPorId(long id){
        Optional<ContaBancaria> optContaBancaria = contaBancariaRepository.findById(id);

        if(optContaBancaria.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optContaBancaria.get();
    }

    // O save serve tanto para adicionar quanto para atualizar.
    // se tiver id, ele atualiza, s enão tiver id ele adiciona.
    public ContaBancaria adicionar(ContaBancaria contaBancaria){
        contaBancaria.setId(0);
        return contaBancariaRepository.save(contaBancaria);
    }

    public ContaBancaria atualizar(long id, ContaBancaria contaBancaria){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        contaBancaria.setId(id);
        return contaBancariaRepository.save(contaBancaria);
    }

    public void deletar(Long id){
        obterPorId(id);

        contaBancariaRepository.deleteById(id);
    }

}
