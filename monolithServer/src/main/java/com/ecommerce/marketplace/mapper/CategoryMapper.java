package com.ecommerce.marketplace.mapper;

import com.ecommerce.marketplace.DTO.CategoryDTO;
import com.ecommerce.marketplace.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDto(Category category);

    List<CategoryDTO> categoriesToCategoryDtos(List<Category> categories);

    Category categoryDtoToCategory(CategoryDTO categoryDTO);
}
