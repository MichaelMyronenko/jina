package com.develop.jina1.order;

import com.develop.jina1.basket.Basket;
import com.develop.jina1.basket.BasketService;
import com.develop.jina1.error.NotFoundException;
import com.develop.jina1.order.productRef.OrderProductRef;
import com.develop.jina1.user.User;
import com.develop.jina1.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.develop.jina1.order.OrderStatus.IN_PROCESS;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BasketService basketService;
    private final UserService userService;

    public Set<OrderDto> getOrdersByUser(Long userId) {
        return orderRepository.findAllByUserId(userId)
                .stream().map(orderMapper::mapToDto)
                .collect(Collectors.toSet());
    }

    public OrderDto getOrder(Long orderId) {
        Order order = processOrder(orderId);
        return orderMapper.mapToDto(order);
    }

    public OrderDto createOrder(Long authenticatedUserId,
                                OrderCommand orderCommand) {
        Basket basket = basketService.processBasket(orderCommand.getBasketId());
        User user = userService.processUser(authenticatedUserId);
        Order order = Order.builder()
                .userId(user.getId())
                .orderStatus(IN_PROCESS)
                .products(getProducts(basket))
                .totalPrice(basketService.calculateBasketTotalPrice(basket))
                .build();

        order = orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    public OrderDto updateOrder(Long authenticatedUserId,
                                Long orderId,
                                OrderCommand orderCommand) {
        Order order = processOrder(orderId);
        Basket basket = basketService.processBasket(orderCommand.getBasketId());

        order.setProducts(getProducts(basket));
        order.setOrderStatus(IN_PROCESS);
        order.setTotalPrice(basketService.calculateBasketTotalPrice(basket));

        return orderMapper.mapToDto(order);
    }

    public Order processOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Not found order with id " + orderId));
    }

    Set<OrderProductRef> getProducts(Basket basket) {
        return basket.getProducts().stream()
                .map(orderMapper::mapBasketProdRefToOrderProdRef)
                .collect(Collectors.toSet());
    }
}
