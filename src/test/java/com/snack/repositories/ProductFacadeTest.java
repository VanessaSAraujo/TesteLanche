package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.facade.ProductFacade;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class ProductFacadeTest {
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product produtoTeste;
    private ProductFacade productFacade;

    @BeforeEach
    void setUp() {
         productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);
        productFacade = new ProductFacade(productApplication);

        produtoTeste = new Product(1, "Produto Teste", 10.0f, "src/test/resources/imagem.png");
        productFacade.append(produtoTeste);
    }

    @Test
    void testarListarTodosOsProdutos() {
        List<Product> produtos = productFacade.getAll();
        Assertions.assertFalse(produtos.isEmpty());
        Assertions.assertEquals(1, produtos.size());
        Assertions.assertEquals("Produto Teste", produtos.get(0).getDescription());
    }

    @Test
    void testarObterProdutoPorId() {
        Product produto = productFacade.getById(1);
        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Produto Teste", produto.getDescription());
    }

    @Test
    void testarConfirmarExistenciaDoProduto() {
        Assertions.assertTrue(productFacade.exists(1));
    }

    @Test
    void testarRetornarFalsoParaProdutoInexistente() {
        Assertions.assertFalse(productFacade.exists(999));
    }

    @Test
    void testarAdicionarNovoProdutoComSucesso() {
        Product novoProduto = new Product(2, "Novo Produto", 20.0f, "src/test/resources/imagem.png");
        productFacade.append(novoProduto);

        Assertions.assertTrue(productFacade.exists(2));
        File imagemSalva = new File(productService.getImagePathById(2));
        Assertions.assertTrue(imagemSalva.exists());
    }

    @Test
    void testarRemoverProdutoExistente() {
        Assertions.assertTrue(productFacade.exists(1));

        productFacade.remove(1);

        Assertions.assertFalse(productFacade.exists(1));
        Assertions.assertThrows(Exception.class, () -> productService.getImagePathById(1));
    }
}
