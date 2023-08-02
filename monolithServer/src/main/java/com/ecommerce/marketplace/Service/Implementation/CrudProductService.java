package com.ecommerce.marketplace.Service.Implementation;

import com.ecommerce.marketplace.Entity.Category;
import com.ecommerce.marketplace.Entity.Product;
import com.ecommerce.marketplace.Entity.User;
import com.ecommerce.marketplace.Exception.ResourceNotFoundException;
import com.ecommerce.marketplace.Repository.CategoryRepository;
import com.ecommerce.marketplace.Repository.ProductRepository;
import com.ecommerce.marketplace.Repository.UserRepository;
import com.ecommerce.marketplace.Service.ICrudProductService;
import com.ecommerce.marketplace.Utils.PagingCommons;
import com.ecommerce.marketplace.mapper.CategoryMapper;
import com.ecommerce.marketplace.request_response_models.request.ProductRequest;
import com.ecommerce.marketplace.request_response_models.response.PagedResponse;
import com.ecommerce.marketplace.request_response_models.response.ProductReponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudProductService implements ICrudProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    @Autowired
    public CrudProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product save(ProductRequest product) {
        Category category = categoryRepository.findById(product.getCategoryId()).get();
        Product prod = new Product(product.getNameProduct(),
                product.getDescription(),
                product.getPrice(),
                product.isAvailable(),
                product.getQuantity(),
                0,
                category
        );
        return productRepository.save(prod);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * @param id
     * @return Product
     */

    @Override
    public Product getProductById(Long id) throws ResourceNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) product.get();
        throw new ResourceNotFoundException("Product", "ID", id);
    }

    @Override
    public ProductReponse getProductResponse(Product product) {
        ProductReponse productReponse = new ProductReponse();
        BeanUtils.copyProperties(product, productReponse);
        productReponse.setCategory(CategoryMapper.mapper.categoryToCategoryDto(product.getCategory()));
        productReponse.setProductImages(product.getImageUris());
        return productReponse;
    }

    /**
     * @param page,size,sortDir,sort
     * @return List<Product>
     */
    @Override
    public PagedResponse<ProductReponse> getAllProducts(int page, int size, String sortDir, String sort) {
        PageRequest pageReq = PagingCommons.getPagingRequest(page, size, sortDir, sort);
        return new PagedResponse<>(productRepository.findAll(pageReq).map(product -> getProductResponse(product)));
    }

    /**
     * @param providerId,page,size,sortDir,sort
     * @return List<Product>
     */
    @Override
    public PagedResponse<ProductReponse> getProductByProviderId(Long providerId, int page, int size, String sortDir, String sort) throws ResourceNotFoundException {
        Optional<User> provider = userRepository.findById(providerId);

        if (!provider.isPresent()) {
            throw new ResourceNotFoundException("Provider", "ID", providerId);
        }
        PageRequest pageReq = PagingCommons.getPagingRequest(page, size, sortDir, sort);
        return new PagedResponse<>(productRepository.findByCreatedBy(provider.get(), pageReq).map(this::getProductResponse));
    }

    /**
     * @param categoryId,page,size,sortDir,sort
     * @return PagedResponse<ProductResponse>
     */
    @Override
    public PagedResponse<ProductReponse> getProductByCategoryId(Long categoryId, int page, int size, String sortDir, String sort) throws ResourceNotFoundException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            throw new ResourceNotFoundException("Category", "ID", categoryId);
        }
        PageRequest pageReq = PagingCommons.getPagingRequest(page, size, sortDir, sort);
        return new PagedResponse<>(productRepository.findByCategory(category.get(), pageReq).map(this::getProductResponse));
    }
}
