package ru.geekbrains.march.market.core;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import ru.geekbrains.march.market.core.services.OrderService;
import ru.geekbrains.march.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.Collections;

@SpringBootTest(classes = OrderService.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProductService productService;

    @Test
    public void createOrderTest(){
        CartDto cartDto = new CartDto();
        cartDto.setItems(Collections.emptyList());
        cartDto.setTotalPrice(BigDecimal.ZERO);

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart();
        orderService.createOrder("Bob");
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }


}
