package com.veronica.cosmeticos.service.impl;

import com.veronica.cosmeticos.entity.Produto;
import com.veronica.cosmeticos.repository.ProdutoRepository;
import com.veronica.cosmeticos.service.ProdutoService;
import com.veronica.cosmeticos.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto create(Produto produto) {
        validateProduto(produto);
        // garante que id não seja considerado ao criar
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    @Override
    public Produto update(Integer id, Produto produto) {
        validateProduto(produto);
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com id " + id + " não encontrado"));
        // atualiza campos permitidos
        existente.setNome(produto.getNome());
        existente.setCategoria(produto.getCategoria());
        existente.setPreco(produto.getPreco());
        return produtoRepository.save(existente);
    }

    @Override
    public Optional<Produto> findById(Integer id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto com id " + id + " não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    private void validateProduto(Produto p) {
        if (p == null) throw new IllegalArgumentException("Produto não pode ser nulo");
        if (p.getNome() == null || p.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        if (p.getPreco() == null) {
            throw new IllegalArgumentException("Preço é obrigatório");
        }
        if (p.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
    }
}
