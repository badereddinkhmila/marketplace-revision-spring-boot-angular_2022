package com.ecommerce.marketplace.DTO;

import com.ecommerce.marketplace.Entity.Order;
import com.ecommerce.marketplace.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
    private List<Order> orders;
}
