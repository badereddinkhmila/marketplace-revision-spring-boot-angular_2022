package com.ecommerce.marketplace.Entity;

import com.ecommerce.marketplace.Entity.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.annotations.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Slf4j
@Indexed
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "Unique Product name per provider", columnNames = {"nameProduct", "createdBy"})
})
public class Product extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Size(max = 50)
    @Field(termVector = TermVector.YES)
    private String nameProduct;

    @NonNull
    @Size(min = 10)
    @Field(termVector = TermVector.YES)
    private String description;

    private boolean isAvailable;

    @NonNull
    @Field(termVector = TermVector.YES)
    @SortableField
    private Float price;

    @Min(0)
    private int quantity = 0;

    @Min(0)
    private int discount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @IndexedEmbedded
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_rules",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    @JsonIgnore
    private Set<Rule> rules = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    @Size(min = 1, max = 4, message = "A product needs to display at least one image and at most four !")
    private List<ProductImageFile> images;

    public Product(String nameProduct, String description, Float price, boolean isAvailable, int quantity, int discount, Category category) {
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.quantity = quantity;
        this.discount = discount;
        this.category = category;
    }

    public List<String> getImageUris() {
        if (images == null) return new ArrayList<>();
        List<String> imagesUri = images.stream().map(image -> {
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/products/downloadFile/")
                    .path(String.valueOf(image.getId()))
                    .toUriString();
        }).collect(Collectors.toList());
        return imagesUri;
    }
}