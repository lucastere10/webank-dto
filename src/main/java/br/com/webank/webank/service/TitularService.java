package br.com.webank.webank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.webank.webank.model.ContaBancaria;
import br.com.webank.webank.model.Endereco;
import br.com.webank.webank.model.Titular;
import br.com.webank.webank.repository.TitularRepository;

@Service
public class TitularService {
    
    @Autowired
    private TitularRepository titularRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ContaBancariaService contaBancariaService;

    public List<Titular> obterTodos(){
        return titularRepository.findAll();
    }

    public Titular obterPorId(long id){
        Optional<Titular> optTitular = titularRepository.findById(id);

        if(optTitular.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optTitular.get();
    }

    @Transactional
    public Titular adicionar(Titular titular){
   
        // 1° Cadastrar o titular para poder cadastrar as demais entidades.
        titular.setId(0);
        titular = titularRepository.save(titular);

        // 2° Aqui eu cadastro o endereco
        Endereco endereco = titular.getEndereco();
        if(endereco.getId() == 0){
            endereco.setTitular(titular);
            endereco = enderecoService.adicionar(endereco);

            titular.setEndereco(endereco);
        }
        //3° Aqui cadastramos as contas bancárias.
        List<ContaBancaria> adicionadas = new ArrayList<>();
        for(ContaBancaria contaBancaria : titular.getContas()){

            if(contaBancaria.getId() == 0){

                contaBancaria.setTitular(titular);
                contaBancaria = contaBancariaService.adicionar(contaBancaria);

                adicionadas.add(contaBancaria);
            }
        }

        titular.setContas(adicionadas);
        return titular;
    }

    public Titular atualizar(long id, Titular titular){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        titular.setId(id);
        return titularRepository.save(titular);
     
    }

    public void deletar(Long id){
        obterPorId(id);

        titularRepository.deleteById(id);
    }

}
