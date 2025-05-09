package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ProductApplicationTest {
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product produtoTeste;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);
        produtoTeste = new Product(1, "X-burguer", 15.5f, "src/test/resources/imagem.png");
        productApplication.append(produtoTeste);
//        produtoComImagemInvalida = new Product(2, "X-Salada", 25.0f, "caminho_inexistente.jpg");
    }

//    1. Listar todos os produtos do repositório

    @Test
    public void testarListarProdutos() {
        List<Product> produtos = productApplication.getAll();

        Assertions.assertEquals(1, produtos.size());
    }

//    2. Obter um produto por ID válido

    @Test
    public void testarObterProdutoIDValido() {
        Product produto = productApplication.getById(1);

        Assertions.assertEquals("X-burguer", produto.getDescription());
    }

//    3. Retornar nulo ou erro ao tentar obter produto por ID inválido

    @Test
    public void testarObterProdutoIDInvalido() {
        Assertions.assertThrows(Exception.class, () -> productApplication.getById(5));
    }

//    4. Verificar se um produto existe por ID válido

    @Test
    public void verificarProdutoIDValido() {
        Assertions.assertTrue(productApplication.exists(1));
    }

//    5. Retornar falso ao verificar a existência de um produto com ID inválido

    @Test
    public void verificarExistenciaProdutoIDInvalido () {
        Assertions.assertFalse(productApplication.exists(5));
    }

//    6. Adicionar um novo produto e salvar sua imagem corretamente********

    @Test
    public void testarAdicionarProdutoESalvarImagem() {
        Assertions.assertEquals(1, productApplication.getAll().size());
        Assertions.assertTrue(new File(productService.getImagePathById(1)).exists());
    }
//    7. Remover um produto existente e deletar sua imagem

    @Test
    public void testarRemoverProdutoEDeletarImagem() {
        productApplication.remove(1);

        Assertions.assertFalse(productApplication.exists(1));
        Assertions.assertThrows(Exception.class, () -> productService.getImagePathById(1));
    }
//    8. Não alterar o sistema ao tentar remover um produto com ID inexistente*******


//    9. Atualizar um produto existente e substituir sua imagem


}
