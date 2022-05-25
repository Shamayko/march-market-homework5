package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductService productService;


    public void createOrder(String username) {

        CartDto cartDto = cartServiceIntegration.getCurrentCart();
        Order order = new Order();
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setUsername(username);
        order.setItems(parseCartItems(cartDto.getItems(), order));
        orderRepository.save(order);

    }

    public List<OrderItem> parseCartItems(List<CartItemDto> list, Order order) {

        List<OrderItem> listOrderItems = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OrderItem orderItem = new OrderItem();
            Product product = productService.findById(list.get(i).getProductId()).orElseThrow();
            orderItem.setPrice(list.get(i).getPrice());
            orderItem.setProduct(product);
            orderItem.setPricePerProduct(list.get(i).getPricePerProduct());
            orderItem.setQuantity(list.get(i).getQuantity());
            orderItem.setOrder(order);
            listOrderItems.add(orderItem);
        }
        return listOrderItems;

    }
}
