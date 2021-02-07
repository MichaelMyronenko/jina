package com.develop.jina1.adminPanel.category;

import com.querydsl.core.types.Predicate;

import java.util.List;

public class CategoryProvider {
    public static Category getCategory(String categoryTitle) {
        return Category.builder()
                .title(categoryTitle)
                .id(1L)
                .build();
    }

    public static CategoryDto getCategoryDto(String categoryTitle) {
        return CategoryDto.builder()
                .title(categoryTitle)
                .id(1L)
                .build();
    }

    public static List<CategoryDto> getCategoryDtoList() {
        return List.of(getCategoryDto("Laptop"),
                getCategoryDto("Alcohol"),
                getCategoryDto("Graphic adapter"),
                getCategoryDto("Clothes"));
    }

    public static List<Category> getCategoryList() {
        return List.of(getCategory("Laptop"),
                getCategory("Alcohol"),
                getCategory("Graphic adapter"),
                getCategory("Clothes"));
    }

    public static CategoryCommand getCategoryCommand(String title) {
        return CategoryCommand.builder()
                .title(title)
                .build();
    }

    public static CategoryDto getMappedCategoryDto(Category category) {
        CategoryMapper categoryMapper = new CategoryMapperImpl();
        return categoryMapper.mapToDto(category);
    }

    public static Predicate getEmptyPredicate() {
        CategoryFilterBuilder categoryFilterBuilder = new CategoryFilterBuilder();
        return categoryFilterBuilder.build(getEmptyFilteringCommand());
    }

    public static CategoryFilterCommand getEmptyFilteringCommand() {
        return new CategoryFilterCommand();
    }

    public static CategoryFilterCommand getFilledFilteringCommand() {
        return CategoryFilterCommand.builder()
                .title("Laptop")
                .id(1L)
                .build();
    }
}
