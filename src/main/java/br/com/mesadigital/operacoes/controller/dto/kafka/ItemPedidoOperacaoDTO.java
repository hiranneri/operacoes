package br.com.mesadigital.operacoes.controller.dto.kafka;

public class ItemPedidoOperacaoDTO {

    private Long idProduto;

    private String nome;

    private float quantidade;

    private float valorTotalItem;

    public ItemPedidoOperacaoDTO() {
    }

    public ItemPedidoOperacaoDTO(Long idProduto, String nome, float quantidade, float valorTotalItem) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorTotalItem = valorTotalItem;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(float valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }

    @Override
    public String toString() {
        return "ItemOperacaoDTO{" +
                "idProduto=" + idProduto +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", valorTotalItem=" + valorTotalItem +
                '}';
    }
}
