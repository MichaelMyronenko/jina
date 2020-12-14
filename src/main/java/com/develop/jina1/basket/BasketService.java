package com.develop.jina1.basket;

import com.develop.jina1.basket.productRef.BasketProductRef;
import com.develop.jina1.basket.productRef.BasketProductRefCommand;
import com.develop.jina1.basket.productRef.BasketProductRefMapper;
import com.develop.jina1.basket.productRef.BasketProductRefRepository;
import com.develop.jina1.error.CalculationException;
import com.develop.jina1.error.NotFoundException;
import com.develop.jina1.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BasketService {

    private static final Double EMPTY_BASKET_TOTAL_PRICE = 0D;

    private final BasketMapper basketMapper;
    private final BasketProductRefMapper basketProductRefMapper;
    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final BasketProductRefRepository basketProductRefRepository;

    @Transactional(readOnly = true)
    public BasketDto getBasket(Long authenticatedUserId) {
        Basket basket = processBasket(authenticatedUserId);
        BasketDto basketDto = basketMapper.mapToDto(basket);
        basketDto.setTotal_price(calculateBasketTotalPrice(basket));
        return basketDto;
    }

    @Transactional
    public BasketDto addProductToBasket(Long authenticatedUserId, BasketProductRefCommand basketProductRefCommand) {
        productService.processProduct(basketProductRefCommand.getProductId());
        Basket basket = processBasket(authenticatedUserId);

        BasketProductRef basketProductRef = basketProductRefMapper.mapToEntity(basketProductRefCommand);
        basketProductRef.setBasketId(authenticatedUserId);
        basket = addProductRef(basket, basketProductRef);

        BasketDto basketDto = basketMapper.mapToDto(basket);
        basketDto.setTotal_price(calculateBasketTotalPrice(basket));
        return basketDto;
    }

    @Transactional
    public BasketDto removeProductFomBasket(Long authenticatedUserId, Long productId) {
        Basket basket = processBasket(authenticatedUserId);
        productService.processProduct(productId);

        BasketProductRef basketProductRef = processProductRef(productId, authenticatedUserId);
        basketProductRef.setBasketId(authenticatedUserId);
        basket.removeProduct(basketProductRef);

        BasketDto basketDto = basketMapper.mapToDto(basketRepository.save(basket));
        basketDto.setTotal_price(calculateBasketTotalPrice(basket));
        return basketDto;
    }

    public Double calculateBasketTotalPrice(Basket basket) {
        if (basket.getProducts().isEmpty()) {
            return EMPTY_BASKET_TOTAL_PRICE;
        }
        return basket.getProducts().stream()
                .map(productRef -> (productService.processProduct(productRef.getProductId()).getPrice() * productRef.getQuantity()))
                .reduce(Double::sum)
                .orElseThrow(() -> new CalculationException("Some stuff happened, I can't calculate the basket total price!"));
    }

    public Basket processBasket(Long userId) {
        return basketRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Not found basket with user id " + userId));
    }

    private BasketProductRef processProductRef(Long productId, Long basketId) {
        return basketProductRefRepository.findByProductIdAndBasket(productId, basketId)
                .orElseThrow(() -> new NotFoundException("Not found product reference!"));
    }

    private Basket addProductRef(Basket basket, BasketProductRef basketProductRef) {
        basket.getProducts().remove(basketProductRef);
        basket.addProduct(basketProductRef);
        return basketRepository.save(basket);
    }
}