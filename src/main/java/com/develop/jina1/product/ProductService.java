package com.develop.jina1.product;

import com.develop.jina1.adminPanel.category.CategoryService;
import com.develop.jina1.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final ProductFilterBuilder productFilterBuilder;

    public ProductDto getProduct(Long productId) {
        return productMapper.mapEntityToDto(processProduct(productId));
    }

    public List<ProductDto> getProducts(ProductFilteringCommand productFilteringCommand) {
        return productRepository
                .findAll(productFilterBuilder.build(productFilteringCommand))
                .stream().map(productMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public ProductDto saveProduct(ProductCommand productCommand) {
        Product product = productMapper.mapDtoToEntity(productCommand);
        categoryService.processCategory(productCommand.getCategoryId());
        product.setCreatedAt(Instant.now());
        product.setCategoryId(productCommand.getCategoryId());
        product = productRepository.save(product);
        return productMapper.mapEntityToDto(product);
    }

    public ProductDto updateProduct(Long productId,
                                    ProductCommand productCommand) {
        Product product = processProduct(productId);
        productMapper.dtoToEntity(productCommand, product);
        product.setCreatedAt(Instant.now());
        return productMapper.mapEntityToDto(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.delete(processProduct(productId));
    }

    public Product processProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Not found product with id " + productId));
    }
}
