package com.ecommerce.marketplace.Entity;

import com.ecommerce.marketplace.Entity.audit.UserDateAudit;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Category extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(min = 3, max = 50)
    @Field(termVector = TermVector.YES)
    private String name;

    @NonNull
    @Field(termVector = TermVector.YES)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    private Collection<Product> products = new ArrayList<>();


}
