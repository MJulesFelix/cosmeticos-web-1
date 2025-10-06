package com.veronica.cosmeticos.dto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Integer id;
    private String nome;
    private String categoria;
    private BigDecimal preco;

    public ProdutoDTO() {}

    
    public ProdutoDTO(Integer id, String nome, String categoria, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}
