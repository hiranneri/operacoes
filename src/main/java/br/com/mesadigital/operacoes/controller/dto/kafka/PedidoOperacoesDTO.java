package br.com.mesadigital.operacoes.controller.dto.kafka;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoOperacoesDTO {

    private Long id;
    private List<ItemPedidoOperacaoDTO> itens;
    private LocalDateTime dataHora;
    private BigDecimal total;
    private List<String> formasPagamento;
    private BigDecimal troco;
    private Long caixaId;
    private String nomeUsuario;
    private String clienteId;
    private String status;

    public static String PEDIDO_PRONTO = "PRONTO";
    public static String PEDIDO_QUITADO = "QUITADO";

    public PedidoOperacoesDTO() {
    }

    public PedidoOperacoesDTO(Long id,List<ItemPedidoOperacaoDTO> itens, LocalDateTime dataHora, BigDecimal total,
                              List<String> formasPagamento, BigDecimal troco, Long caixaId,
                              String nomeUsuario, String clienteId, String status) {
        this.id = id;
        this.itens = itens;
        this.dataHora = dataHora;
        this.total = total;
        this.formasPagamento = formasPagamento;
        this.troco = troco;
        this.caixaId = caixaId;
        this.nomeUsuario = nomeUsuario;
        this.clienteId = clienteId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemPedidoOperacaoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoOperacaoDTO> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<String> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<String> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public Long getCaixaId() {
        return caixaId;
    }

    public void setCaixaId(Long caixaId) {
        this.caixaId = caixaId;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PedidoOperacoesDTO{" +
                "id=" + id +
                "itens=" + itens +
                ", dataHora=" + dataHora +
                ", total=" + total +
                ", formasPagamento=" + formasPagamento +
                ", troco=" + troco +
                ", caixaId=" + caixaId +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", clienteId='" + clienteId + '\'' +
                '}';
    }
}
