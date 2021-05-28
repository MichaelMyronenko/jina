package com.develop.jina1.order;

import com.develop.jina1.security.userLogin.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<OrderDto>> getOrders(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return new ResponseEntity<>(orderService.getOrdersByUser(authenticatedUser.getId()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                @RequestBody OrderCommand orderCommand) {
        return new ResponseEntity<>(orderService.createOrder(authenticatedUser.getId(), orderCommand), HttpStatus.CREATED);
    }

    @PutMapping("{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                @PathVariable Long orderId,
                                                @RequestBody OrderCommand orderCommand) {
        return new ResponseEntity<>(orderService.updateOrder(authenticatedUser.getId(),
                orderId, orderCommand), HttpStatus.OK);
    }
}
