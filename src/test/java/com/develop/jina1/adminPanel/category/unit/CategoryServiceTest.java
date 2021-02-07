package com.develop.jina1.adminPanel.category.unit;

import com.develop.jina1.adminPanel.category.*;
import com.develop.jina1.error.ConflictException;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryFilterBuilder categoryFilterBuilder;

    @Mock
    CategoryMapper categoryMapper;

    @Test
    void getCategory() {
        Category category = CategoryProvider.getCategory("Laptop");

        when(categoryRepository.findById(any()))
                .thenReturn(Optional.of(category));
        when(categoryMapper.mapToDto(category))
                .thenReturn(CategoryProvider.getMappedCategoryDto(category));

        CategoryDto categoryDto = categoryService.getCategory(1L);

        assertThat(categoryDto.getTitle())
                .isEqualTo("Laptop");
        assertThat(categoryDto.getId())
                .isEqualTo(1L);
    }

    @Test
    void getCategories() {
        CategoryFilterCommand categoryFilterCommand = CategoryProvider.getEmptyFilteringCommand();
        Predicate predicate = CategoryProvider.getEmptyPredicate();

        when(categoryRepository.findAll(predicate))
                .thenReturn(CategoryProvider.getCategoryList());
        when(categoryFilterBuilder.build(categoryFilterCommand))
                .thenReturn(predicate);

        List<CategoryDto> categories = categoryService.getCategories(categoryFilterCommand);

        assertThat(categories.isEmpty())
                .isEqualTo(false);
        assertThat(categories.size())
                .isEqualTo(4);
    }

    @Test
    void createCategory() {
        String categoryTitle = "Laptop";
        CategoryCommand categoryCommand = CategoryProvider.getCategoryCommand(categoryTitle);
        Category category = CategoryProvider.getCategory(categoryTitle);

        when(categoryRepository.existsByTitle(categoryTitle))
                .thenReturn(false);
        when(categoryRepository.save(category))
                .thenReturn(category);
        when(categoryMapper.mapToDto(category))
                .thenReturn(CategoryProvider.getMappedCategoryDto(category));
        when(categoryMapper.mapToEntity(categoryCommand))
                .thenReturn(category);

        CategoryDto categoryDto = categoryService.createCategory(categoryCommand);

        assertThat(categoryDto.getTitle())
                .isEqualTo(categoryTitle);
    }

    @Test
    void tryToCreateExistentCategory_thenReturn_ConflictException() {
        String categoryTitle = "Laptop";
        CategoryCommand categoryCommand = CategoryProvider.getCategoryCommand(categoryTitle);

        when(categoryRepository.existsByTitle(categoryTitle))
                .thenReturn(true);

        assertThrows(ConflictException.class, () -> categoryService.createCategory(categoryCommand));
    }
}