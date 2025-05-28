package br.com.mesadigital.operacoes.model;

import br.com.mesadigital.operacoes.controller.dto.kafka.ItemPedidoOperacaoDTO;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document
public class PedidoOperacoes {

    @Id
    private String id;

    private Long clienteId;

    private List<ItemPedidoOperacaoDTO> itens;

    private LocalDateTime dataHora;

    private BigDecimal total;

    private List<String> formasPagamento;

    private BigDecimal troco;

    private Long caixaId;

    private String nomeUsuario;

    private String status;

    public PedidoOperacoes() {
    }

    public PedidoOperacoes(String id, Long clienteId, List<ItemPedidoOperacaoDTO> itens, LocalDateTime dataHora, BigDecimal total,
                           List<String> formasPagamento, BigDecimal troco, Long caixaId, String nomeUsuario, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.dataHora = dataHora;
        this.total = total;
        this.formasPagamento = formasPagamento;
        this.troco = troco;
        this.caixaId = caixaId;
        this.nomeUsuario = nomeUsuario;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PedidoOperacoes{" +
                "id='" + id + '\'' +
                ", clienteId=" + clienteId +
                ", itens=" + itens +
                ", dataHora=" + dataHora +
                ", total=" + total +
                ", formasPagamento=" + formasPagamento +
                ", troco=" + troco +
                ", caixaId=" + caixaId +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
