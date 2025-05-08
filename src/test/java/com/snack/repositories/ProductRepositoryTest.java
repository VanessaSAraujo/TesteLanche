package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private Product product01;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        product01 = new Product(1, "X-burguer", 24.5f, "imagem.png");
    }

//    1. Verificar se um produto é adicionado corretamente ao repositório (List)

    @Test
    public void testarProdutoAdicionadoCorretamente() {
        productRepository.append(product01);

        Assertions.assertTrue(productRepository.getAll().contains(product01));
    }

//    2. Verificar se é possível recuperar um produto usando seu ID

    @Test
    public void testarRecuperarProdutoPeloId() {
        productRepository.append(product01);

        Assertions.assertEquals("X-burguer", productRepository.getById(1).getDescription());
    }

//    3. Confirmar se um produto existe no repositório (List)

    @Test
    public void validarProdutoNoRepositorio() {
        productRepository.append(product01);

        Assertions.assertTrue(productRepository.exists(1));
    }
}
