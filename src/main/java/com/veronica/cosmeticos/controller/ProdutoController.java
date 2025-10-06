package com.veronica.cosmeticos.controller;

import com.veronica.cosmeticos.entity.Produto;
import com.veronica.cosmeticos.service.ProdutoService;
import com.veronica.cosmeticos.dto.ProdutoDTO;
import com.veronica.cosmeticos.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    private ProdutoDTO toDTO(Produto p) {
        return new ProdutoDTO(p.getId(), p.getNome(), p.getCategoria(), p.getPreco());
    }

    private Produto toEntity(ProdutoDTO dto) {
        Produto p = new Produto();
        p.setId(dto.getId());
        p.setNome(dto.getNome());
        p.setCategoria(dto.getCategoria());
        p.setPreco(dto.getPreco());
        return p;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> lista = produtoService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Integer id) {
        return produtoService.findById(id)
                .map(p -> ResponseEntity.ok(toDTO(p)))
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoDTO produtoDTO) {
        Produto p = toEntity(produtoDTO);
        Produto criado = produtoService.create(p);
        URI uri = URI.create("/api/produtos/" + criado.getId());
        return ResponseEntity.created(uri).body(toDTO(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
        Produto p = toEntity(produtoDTO);
        Produto atualizado = produtoService.update(id, p);
        return ResponseEntity.ok(toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
