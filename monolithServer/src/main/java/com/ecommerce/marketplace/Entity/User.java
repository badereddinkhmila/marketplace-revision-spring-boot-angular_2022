package com.ecommerce.marketplace.Entity;

import com.ecommerce.marketplace.Entity.audit.DateAudit;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "username"
                }),
                @UniqueConstraint(columnNames = {
                        "email"
                })}
)
@AllArgsConstructor()
@NoArgsConstructor
@ToString
@Getter
@Setter
@Indexed()
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Field()
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(nullable = false, updatable = false)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    @ContainedIn
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    @ContainedIn
    private List<Rule> rules = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    @ContainedIn
    private List<Product> products = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
