package com.develop.jina1.basket;

import com.develop.jina1.basket.productRef.BasketProductRefCommand;
import com.develop.jina1.security.userLogin.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket")
@AllArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @GetMapping
    public ResponseEntity<BasketDto> getBasket(@AuthenticationPrincipal AuthenticatedUser user) {
        return new ResponseEntity<>(basketService.getBasket(user.getId()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BasketDto> addProduct(@AuthenticationPrincipal AuthenticatedUser user,
                                                @RequestBody BasketProductRefCommand basketProductRefCommand) {
        return new ResponseEntity<>(basketService.addProductToBasket(user.getId(), basketProductRefCommand), HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<BasketDto> removeProduct(@AuthenticationPrincipal AuthenticatedUser user,
                                                   @PathVariable Long productId) {
        return new ResponseEntity<>(basketService.removeProductFomBasket(user.getId(), productId), HttpStatus.OK);
    }
}
