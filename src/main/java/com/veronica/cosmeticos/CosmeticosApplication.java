package com.veronica.cosmeticos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.veronica.cosmeticos.service.ProdutoService;
import com.veronica.cosmeticos.entity.Produto;

import java.math.BigDecimal;

@SpringBootApplication
public class CosmeticosApplication {
    public static void main(String[] args) {
        SpringApplication.run(CosmeticosApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProdutoService produtoService) {
        return args -> {
            System.out.println("--- Aplicação iniciada (verificando produtos) ---");
            // imprime quantidade inicial
            System.out.println("Nº produtos: " + produtoService.findAll().size());

            // Exemplo de criação de teste (descomente se quiser criar um produto ao iniciar)
            /*
            Produto p = new Produto();
            p.setNome("Produto de Teste");
            p.setCategoria("Teste");
            p.setPreco(new BigDecimal("9.90"));
            Produto criado = produtoService.create(p);
            System.out.println("Criado produto de teste com id: " + criado.getId());
            */
        };
    }
}
