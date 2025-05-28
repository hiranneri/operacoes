package br.com.mesadigital.operacoes.controller;

import br.com.mesadigital.operacoes.service.OperacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operacoespedido")
public class OperacoesPedidoController {

    @Autowired
    OperacoesService operacoesService;

    @PatchMapping("/pronto/{id}")
    public ResponseEntity<Void> pedidoPronto(@PathVariable String id) {
        operacoesService.atualizarStatusPedidoParaPronto(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
