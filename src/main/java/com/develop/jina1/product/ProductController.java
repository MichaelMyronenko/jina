package com.develop.jina1.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(ProductFilteringCommand productFilteringCommand) {
        return new ResponseEntity<>(productService.getProducts(productFilteringCommand), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductCommand productCommand) {
        return new ResponseEntity<>(productService.saveProduct(productCommand), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,
                                                    @RequestBody ProductCommand productCommand) {
        return new ResponseEntity<>(productService.updateProduct(productId, productCommand), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
