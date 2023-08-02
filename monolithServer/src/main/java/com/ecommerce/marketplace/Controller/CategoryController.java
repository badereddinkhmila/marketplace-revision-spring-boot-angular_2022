package com.ecommerce.marketplace.Controller;

import com.ecommerce.marketplace.DTO.CategoryDTO;
import com.ecommerce.marketplace.Entity.Category;
import com.ecommerce.marketplace.Service.Implementation.CategoryService;
import com.ecommerce.marketplace.Utils.AppConstants;
import com.ecommerce.marketplace.request_response_models.response.PagedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<CategoryDTO>> getAllCategories(
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sortDir", required = false) String sortDir,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        return ResponseEntity.ok(categoryService.getAllCategories(page, size, sortDir, sort));
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
