package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.fail;

public class ProductServiceTest {
    private ProductService productService;
    private Product produtoComImagemValida;
    private Product produtoComImagemInvalida;
    private final String imagemValida = "C:\\Users\\Nessa\\imagem.png";

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        produtoComImagemValida = new Product(1, "X-burguer", 15.5f, "src/test/resources/imagem.png");
        produtoComImagemInvalida = new Product(2, "X-Salada", 25.0f, "caminho_inexistente.jpg");
    }


//    1. Salvar um produto com imagem válida

    @Test
    public void testarSalvarProdutoComImagemValida() {
//        Assertions.assertDoesNotThrow(() -> productService.save(produtoComImagemInvalida));
        boolean resultado = productService.save(produtoComImagemValida);

        Assertions.assertTrue(resultado);

    }


//    2. Salvar um produto com imagem inexistente

    @Test
    public void testarProdutoImagemInexistente() {

        boolean resultado = productService.save(produtoComImagemInvalida);
        Assertions.assertFalse(resultado);

    }

//    3. Atualizar um produto existente

    @Test
    public void testarAtualizarProdutoExistente() {
        productService.save(produtoComImagemValida);

        Product produtoAtualizado = new Product(1, "X-burguer Duplo", 18.0f, imagemValida);
        productService.update(produtoAtualizado);

        File imagemAtualizada = new File("src\\test\\resources\\imagem.png");
        Assertions.assertTrue(imagemAtualizada.exists());
        Assertions.assertEquals("X-burguer Duplo", produtoAtualizado.getDescription());

    }


//    4. Remover um produto existente

    @Test
    public void testarRemoverProdutoExistente() {
        productService.save(produtoComImagemValida);

        productService.remove(produtoComImagemValida.getId());

        File imagem = new File("src\\test\\resources\\imagem.png");
        Assertions.assertTrue(imagem.exists());
    }

//    5. Obter caminho da imagem por ID

    @Test
    public void testarCaminhoImagemId() {
        productService.save(produtoComImagemValida);

        String caminho = productService.getImagePathById(produtoComImagemValida.getId());
        Assertions.assertNotNull(caminho);
    }
}
