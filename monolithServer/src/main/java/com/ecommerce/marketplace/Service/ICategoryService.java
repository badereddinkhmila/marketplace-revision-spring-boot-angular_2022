package com.ecommerce.marketplace.Service;

import com.ecommerce.marketplace.DTO.CategoryDTO;
import com.ecommerce.marketplace.Entity.Category;
import com.ecommerce.marketplace.request_response_models.response.PagedResponse;

public interface ICategoryService {
    PagedResponse<CategoryDTO> getAllCategories(int page, int size, String sortDir, String sort);

    CategoryDTO addCategory(Category category);

    CategoryDTO getCategoryById(Long id);

    void deleteCategory(Long id);
}
