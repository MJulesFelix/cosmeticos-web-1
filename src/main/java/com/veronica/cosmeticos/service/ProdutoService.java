package com.veronica.cosmeticos.service;

import com.veronica.cosmeticos.entity.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    Produto create(Produto produto);
    Produto update(Integer id, Produto produto);
    Optional<Produto> findById(Integer id);
    List<Produto> findAll();
    void delete(Integer id);
}
