package com.ecommerce.marketplace.Controller;

import com.ecommerce.marketplace.DTO.UserDTO;
import com.ecommerce.marketplace.Entity.RefreshToken;
import com.ecommerce.marketplace.Exception.TokenRefreshException;
import com.ecommerce.marketplace.Repository.UserRepository;
import com.ecommerce.marketplace.Security.JwtTokenProvider;
import com.ecommerce.marketplace.Security.UserPrincipal;
import com.ecommerce.marketplace.Service.Implementation.RefreshTokenService;
import com.ecommerce.marketplace.Service.Implementation.UserService;
import com.ecommerce.marketplace.request_response_models.request.LoginRequest;
import com.ecommerce.marketplace.request_response_models.request.RefreshTokenRequest;
import com.ecommerce.marketplace.request_response_models.request.SignUpRequest;
import com.ecommerce.marketplace.request_response_models.response.ApiResponse;
import com.ecommerce.marketplace.request_response_models.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    private final RefreshTokenService refreshTokenService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String accessToken = tokenProvider.generateAccessToken(userPrincipal);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userPrincipal.getId());

        return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, refreshToken.getToken()));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        UserDTO result = userService.createUser(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/v1/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = tokenProvider.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not valid!"));
    }
}
