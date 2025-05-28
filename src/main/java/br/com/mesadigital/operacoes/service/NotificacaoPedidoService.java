package br.com.mesadigital.operacoes.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoPedidoService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificacaoPedidoService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topicPattern = "pedido\\-.*", groupId = "notificador")
    public void notificarStatusPedido(String pedido) {
        messagingTemplate.convertAndSend("/topic/status-pedidos", pedido);
    }
}
