package com.ecommerce.marketplace.Controller;

import com.ecommerce.marketplace.Entity.*;
import com.ecommerce.marketplace.Service.Implementation.CategoryService;
import com.ecommerce.marketplace.Service.Implementation.CrudProductService;
import com.ecommerce.marketplace.Service.Implementation.ProductImageService;
import com.ecommerce.marketplace.Service.Implementation.RuleService;
import com.ecommerce.marketplace.Utils.AppConstants;
import com.ecommerce.marketplace.request_response_models.request.ProductRequest;
import com.ecommerce.marketplace.request_response_models.response.PagedResponse;
import com.ecommerce.marketplace.request_response_models.response.ProductReponse;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final CrudProductService crudProductService;
    private final RuleService ruleService;
    private final CategoryService categoryService;

    private final ProductImageService productImageService;

    @Autowired
    public ProductController(CrudProductService crudProductService,
                             RuleService ruleService,
                             CategoryService categoryService,
                             ProductImageService productImageService) {
        this.crudProductService = crudProductService;
        this.ruleService = ruleService;
        this.categoryService = categoryService;
        this.productImageService = productImageService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ProductReponse>> getAllProducts(
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @PathVariable(name = "sortDir", required = false) String sortDir,
            @PathVariable(name = "sort", required = false) String sort
    ) {
        log.error("shit error here");
        return ResponseEntity.ok(crudProductService.getAllProducts(page, size, sortDir, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReponse> getProductById(
            @PathVariable(name = "id") Long id
    ) {
        ProductReponse product = crudProductService.getProductResponse(crudProductService.getProductById(id));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<PagedResponse<ProductReponse>> getProductsByCategory(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sortDir", required = false) String sortDir,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        return ResponseEntity.ok(crudProductService.getProductByCategoryId(id, page, size, sortDir, sort));
    }

    @GetMapping("/provider/{id}")
    public ResponseEntity<PagedResponse<ProductReponse>> getProductsByProvider(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sortDir", required = false) String sortDir,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        return ResponseEntity.ok(crudProductService.getProductByProviderId(id, page, size, sortDir, sort));
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity addProduct(@Valid @RequestPart(name = "product") ProductRequest product, @RequestPart(name = "files") MultipartFile[] files) throws URISyntaxException {
        Product prod = crudProductService.save(product);
        if (files != null) {
            for (MultipartFile file : files) {
                try {
                    productImageService.store(file, prod.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/products/")
                .path(String.valueOf(prod.getId())).toUriString();
        return ResponseEntity.created(new URI(uri)).build();
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        ProductImageFile productImageFile = productImageService.getProductFileById(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("multipart/form-data"))
                .body(new ByteArrayResource(productImageFile.getData()));
    }

    @GetMapping("/rules")
    public ResponseEntity<?> getRules() throws FileNotFoundException {
        Collection<Rule> rules = this.ruleService.getByType(RuleType.PROMOTION);
        String drl = this.ruleService.createDroolFile(rules, "sample_rule.drt");
        //KieSession kieSession = this.ruleService.loadContainerFromString(drl).newKieSession();
        KieSession kieSession = this.ruleService.createKieSessionFromDRL(drl);
        Category category = new Category();
        category.setId(1L);
        category.setName("sport");
        category.setDescription("sport category description !!");
        /*Product product1 = Product.builder()
                .id(1L)
                .nameProduct("chlaka")
                .description("chlaka b sbo3 ta7mik !")
                .price(200F)
                .quantity(5)
                .isAvailable(true)
                .category(category)
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .nameProduct("chlaka")
                .description("chlaka b sbo3 ta7mik !")
                .price(120F)
                .quantity(5)
                .isAvailable(true)
                .category(category)
                .build();
        kieSession.insert(product1);
        kieSession.insert(product2);*/
        kieSession.fireAllRules();
        kieSession.dispose();
        return ResponseEntity.ok(""/*product2.getDiscount()*/);
    }
}
