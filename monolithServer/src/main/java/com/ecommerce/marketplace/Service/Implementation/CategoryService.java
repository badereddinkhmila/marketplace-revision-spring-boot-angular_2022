package com.ecommerce.marketplace.Service.Implementation;

import com.ecommerce.marketplace.DTO.CategoryDTO;
import com.ecommerce.marketplace.Entity.Category;
import com.ecommerce.marketplace.Exception.ResourceNotFoundException;
import com.ecommerce.marketplace.Repository.CategoryRepository;
import com.ecommerce.marketplace.Service.ICategoryService;
import com.ecommerce.marketplace.Utils.PagingCommons;
import com.ecommerce.marketplace.mapper.CategoryMapper;
import com.ecommerce.marketplace.request_response_models.response.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public PagedResponse<CategoryDTO> getAllCategories(int page, int size, String sortDir, String sort) {
        PageRequest pageReq = PagingCommons.getPagingRequest(page, size, sortDir, sort);
        Page<Category> categories = categoryRepository.findAll(pageReq);
        List<CategoryDTO> categoryDTOS = categoryMapper.categoriesToCategoryDtos(categories.getContent());
        return new PagedResponse<CategoryDTO>(categoryDTOS, categories.getNumber(),
                categories.getSize(), categories.getTotalElements(), categories.getTotalPages(), categories.isLast());
    }

    @Override
    public CategoryDTO addCategory(Category category) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO getCategoryById(Long id) throws ResourceNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) categoryMapper.categoryToCategoryDto(category.get());
        throw new ResourceNotFoundException("Category", "Id", id);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
