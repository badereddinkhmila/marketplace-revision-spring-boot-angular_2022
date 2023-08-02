package com.ecommerce.marketplace.Controller;

import com.ecommerce.marketplace.DTO.UserDTO;
import com.ecommerce.marketplace.Repository.UserRepository;
import com.ecommerce.marketplace.Security.CurrentUser;
import com.ecommerce.marketplace.Security.UserPrincipal;
import com.ecommerce.marketplace.Service.Implementation.UserService;
import com.ecommerce.marketplace.request_response_models.response.UserIdentityAvailability;
import com.ecommerce.marketplace.request_response_models.response.UserSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('CUSTOMER','ROLE_PROVIDER','ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
                currentUser.getEmail());
        return userSummary;
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }
}
