package com.ecommerce.marketplace.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "order_items", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "order_id","product_id"
        })
})
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id",nullable = false)
    private Order order;


    @OneToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id",nullable = false)
    private Product product;

    public Float getTotalPrice(){
        return this.product.getPrice()*this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct orderProduct = (OrderProduct) o;
        return Objects.equals(id, orderProduct.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
