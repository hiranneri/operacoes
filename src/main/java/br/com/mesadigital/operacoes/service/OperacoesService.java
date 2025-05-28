package br.com.mesadigital.operacoes.service;

import br.com.mesadigital.operacoes.controller.dto.kafka.PedidoOperacoesDTO;
import br.com.mesadigital.operacoes.controller.exceptions.BadRequestException;
import br.com.mesadigital.operacoes.model.PedidoOperacoes;
import br.com.mesadigital.operacoes.repository.PedidoOperacoesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OperacoesService {

    @Autowired
    PedidoOperacoesRepository pedidoOperacoesRepository;

    ObjectMapper mapper = new ObjectMapper();

    private final KafkaTemplate<String, String> kafkaPedidoTemplate;

    public OperacoesService(KafkaTemplate<String, String> kafkaPedidoTemplate) {
        this.kafkaPedidoTemplate = kafkaPedidoTemplate;
        // Registrando o módulo para Java 8 Time API (LocalDateTime, LocalDate, etc)
        mapper.registerModule(new JavaTimeModule());

        // Configurando o formato de datas
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * Consome a fila de pedidos criados (Envia ao front que tem pedido pendente)
     * @param pedidoQuitado dados do pedido criado
     */
    @KafkaListener(topics = "pedido-quitado", groupId = "operacoes-service")
    public void aceitarPedido(String pedidoQuitado) {
        try {
            PedidoOperacoesDTO pedidoRecebido = mapper.reader().forType(PedidoOperacoesDTO.class).readValue(pedidoQuitado);
            cadastrarPedidoRealizado(pedidoRecebido);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void atualizarStatusPedidoParaPronto(String id) {
        try {
            PedidoOperacoes pedidoLocalizado = pedidoOperacoesRepository.findById(id)
                    .orElseThrow( () -> new BadRequestException("Pedido não localizado")
            );

            pedidoLocalizado.setStatus(PedidoOperacoesDTO.PEDIDO_PRONTO);

            PedidoOperacoesDTO pedidoProntoDTO = new PedidoOperacoesDTO();

            BeanUtils.copyProperties(pedidoLocalizado, pedidoProntoDTO);
            pedidoProntoDTO.setId(Long.parseLong(pedidoLocalizado.getId()));

            pedidoOperacoesRepository.save(pedidoLocalizado);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public void cadastrarPedidoRealizado(PedidoOperacoesDTO pedidoRealizado) {
        pedidoRealizado.setStatus(PedidoOperacoesDTO.PEDIDO_QUITADO);
        pedidoOperacoesRepository.save(toPedidoOperacoes(pedidoRealizado));
    }

    PedidoOperacoes toPedidoOperacoes(PedidoOperacoesDTO pedidoRealizado) {
        Long clienteId = pedidoRealizado.getClienteId()==null ? 0L : Long.parseLong(pedidoRealizado.getClienteId());

        return new PedidoOperacoes(
                String.valueOf(pedidoRealizado.getId()),
                clienteId,
                pedidoRealizado.getItens(),
                pedidoRealizado.getDataHora(),
                pedidoRealizado.getTotal(),
                pedidoRealizado.getFormasPagamento(),
                pedidoRealizado.getTroco(),
                pedidoRealizado.getCaixaId(),
                pedidoRealizado.getNomeUsuario(),
                pedidoRealizado.getStatus()
        );
    }
}
