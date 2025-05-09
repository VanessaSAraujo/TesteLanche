package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private Product product01;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        product01 = new Product(1, "X-burguer", 24.5f, "C:\\Users\\aluno.fsa\\Downloads\\TesteLanche-main\\TesteLanche-main\\imagem.png");
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

//    4. Testar se um produto é removido corretamente do repositório (List)

    @Test
    public void testarRemocaoDeProduto() {
        productRepository.append(product01);

        productRepository.remove(1);

        Assertions.assertFalse(productRepository.exists(1));
    }

//    5. Verificar se um produto é atualizado corretamente

    @Test
    public void testarAtualizarProduto() {
        productRepository.append(product01);

        Product produtoAtualizado = new Product(1, "X-Salada", 10.6f, "C:\\Users\\aluno.fsa\\Downloads\\TesteLanche-main\\TesteLanche-main\\imagem-2");

        productRepository.update(1, produtoAtualizado);

        Assertions.assertEquals("X-Salada", productRepository.getById(1).getDescription());
        Assertions.assertEquals(10.6f, productRepository.getById(1).getPrice());
        Assertions.assertEquals("C:\\Users\\aluno.fsa\\Downloads\\TesteLanche-main\\TesteLanche-main\\imagem-2", productRepository.getById(1).getImage());
    }

//    6. Testar se todos os produtos armazenados são recuperados corretamente

    @Test
    public void testarRecuperacaoDeTodosProdutos() {
        Product product02 = new Product(2, "X-burguer", 24.5f, "C:\\Users\\aluno.fsa\\Downloads\\TesteLanche-main\\TesteLanche-main\\imagem-2.png");
        productRepository.append(product01);
        productRepository.append(product02);

        List<Product> products = productRepository.getAll();

        Assertions.assertEquals(2,products.size());
    }

//    7. Verificar o comportamento ao tentar remover um produto que não existe

    @Test
    public void testarRemoverProdutoInexistente() {

        Assertions.assertDoesNotThrow(() -> productRepository.remove(5));
    }

//    8. Testar o que acontece ao tentar atualizar um produto que não está no repositório (List)

    @Test
    public void testarAtualizarProdutoInexistente() {
        Product produtoInexistente = new Product(5, "X-Salada", 10.6f, "C:\\Users\\aluno.fsa\\Downloads\\TesteLanche-main\\TesteLanche-main\\imagem-2");

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            productRepository.update(5, produtoInexistente);
        });
    }

//    9. Verificar se o repositório aceita a adição de produtos com IDs duplicados (espera-se que não)

    @Test
    public void testarAdicionarIDsDuplicados() {
        productRepository.append(product01);
        Product produtoDuplicado = new Product(1, "X-Salada", 10.6f, "C:\\Users\\aluno.fsa\\Downloads\\TesteLanche-main\\TesteLanche-main\\imagem-2");
        productRepository.append(produtoDuplicado);

        Assertions.assertEquals(2, productRepository.getAll().size());
    }

//    10. Confirmar que o repositório retorna uma lista vazia ao ser inicializado (List)

    @Test
    public void testarRepositorioInicializadoVazio() {
        Assertions.assertTrue(productRepository.getAll().isEmpty());
    }

}