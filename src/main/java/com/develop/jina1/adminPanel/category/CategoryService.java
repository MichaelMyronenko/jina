package com.develop.jina1.adminPanel.category;

import com.develop.jina1.error.ConflictException;
import com.develop.jina1.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDto getCategory(Long categoryId) {
        return categoryMapper.mapToDto(processCategory(categoryId));
    }

    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll()
                .stream().map(categoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public CategoryDto createCategory(CategoryCommand categoryCommand) {
        throwConflictIfCategoryExists(categoryCommand.getTitle());
        Category category = categoryMapper.mapToEntity(categoryCommand);
        return categoryMapper.mapToDto(categoryRepository.save(category));
    }

    public CategoryDto updateCategory(Long categoryId, CategoryCommand categoryCommand) {
        Category category = processCategory(categoryId);
        throwConflictIfCategoryExists(categoryCommand.getTitle());
        categoryMapper.updateEntity(categoryCommand, category);
        return categoryMapper.mapToDto(categoryRepository.save(category));
    }

    public void deleteCategory(Long categoryId) {
        Category category = processCategory(categoryId);
        categoryRepository.delete(category);
    }

    public Category processCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Not found category with id " + categoryId));
    }

    private boolean isCategoryExistent(String categoryTitle) {
        return categoryRepository.existsByTitle(categoryTitle);
    }

    private void throwConflictIfCategoryExists(String categoryTitle) {
        if (isCategoryExistent(categoryTitle)) {
            throw new ConflictException("Category already exists!");
        }
    }
}
