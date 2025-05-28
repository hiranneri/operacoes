package br.com.mesadigital.operacoes.repository;

import br.com.mesadigital.operacoes.model.PedidoOperacoes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoOperacoesRepository extends MongoRepository<PedidoOperacoes, String> {
}
