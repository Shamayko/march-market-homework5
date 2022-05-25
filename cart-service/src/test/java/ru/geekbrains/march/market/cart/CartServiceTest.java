package ru.geekbrains.march.market.cart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.services.CartService;

import java.math.BigDecimal;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Test
    public void getCurrentCartTest(){
        cartService.clearCart();
        ProductDto productDto = new ProductDto(33L, "test product", BigDecimal.valueOf(222.22), "Food");
        Mockito.doReturn(productDto).when(productServiceIntegration).findById(33L);
        cartService.addToCart(33L);
        cartService.addToCart(33L);
        Assertions.assertEquals(BigDecimal.valueOf(444.44),  cartService.getCurrentCart().getTotalPrice());

    }

    @Test
    public void addToCartTest(){
        ProductDto productDto = new ProductDto(33L, "test product", BigDecimal.valueOf(222.22), "Food");
        Mockito.doReturn(productDto).when(productServiceIntegration).findById(33L);
        cartService.addToCart(33L);
    }

    @Test
    public void removeByIdTest(){
        ProductDto productDto = new ProductDto(33L, "test product", BigDecimal.valueOf(222.22), "Food");
        Mockito.doReturn(productDto).when(productServiceIntegration).findById(33L);
        cartService.addToCart(33L);
        cartService.addToCart(33L);
        cartService.removeById(33L);
        Assertions.assertEquals(BigDecimal.valueOf(222.22), cartService.getCurrentCart().getTotalPrice());
    }
    @Test
    public void clearCartTest(){
        ProductDto productDto = new ProductDto(33L, "test product", BigDecimal.valueOf(222.22), "Food");
        Mockito.doReturn(productDto).when(productServiceIntegration).findById(33L);
        cartService.addToCart(33L);
        cartService.addToCart(33L);
        cartService.clearCart();
        Assertions.assertEquals(0, cartService.getCurrentCart().getItems().size());
    }

}
