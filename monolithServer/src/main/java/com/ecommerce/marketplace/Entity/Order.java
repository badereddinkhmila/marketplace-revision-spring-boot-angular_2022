package com.ecommerce.marketplace.Entity;

import com.ecommerce.marketplace.Entity.audit.UserDateAudit;
import lombok.*;
import org.apache.tomcat.jni.Poll;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends UserDateAudit {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;

    @NotBlank
    @Size(max = 40)
    private String text;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Float getTotalOrderPrice(){
        return orderProducts.stream().map(element -> element.getTotalPrice()).reduce(0F,(totalOrderPrice, orderItemPrice)->totalOrderPrice+orderItemPrice);
    }

    public Float getNumberOfProducts(){
        return orderProducts.stream().map(element -> element.getTotalPrice()).reduce(0F,(totalOrderPrice, orderItemPrice)->totalOrderPrice+orderItemPrice);
    }
}
