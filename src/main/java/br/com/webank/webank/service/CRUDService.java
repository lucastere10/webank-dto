package br.com.webank.webank.service;

import java.util.List;

public interface CRUDService<Req, Res, ID> {

    List<Res> obterTodos();

    Res obterPorId(ID id);

    Res adicionar(Req requestDto);

    Res atualizar(ID id, Req requestDto);

    void deletar(ID id);
}
