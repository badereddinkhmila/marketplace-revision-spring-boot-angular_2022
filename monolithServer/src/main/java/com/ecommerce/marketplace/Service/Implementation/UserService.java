package com.ecommerce.marketplace.Service.Implementation;

import com.ecommerce.marketplace.DTO.UserDTO;
import com.ecommerce.marketplace.Entity.Role;
import com.ecommerce.marketplace.Entity.RoleName;
import com.ecommerce.marketplace.Entity.User;
import com.ecommerce.marketplace.Exception.AppException;
import com.ecommerce.marketplace.Repository.RoleRepository;
import com.ecommerce.marketplace.Repository.UserRepository;
import com.ecommerce.marketplace.Service.IUserService;
import com.ecommerce.marketplace.mapper.UserMapper;
import com.ecommerce.marketplace.request_response_models.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(SignUpRequest signUpRequest) {
        // Creating user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword()
        );
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        log.info("after setting role");
        User result = userRepository.save(user);
        log.info("after saving user");
        return userMapper.userToUserDto(result);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        log.info("hit the service function");
        return userMapper.usersToUserDtos(users);
    }

    @Override
    public UserDTO getUserById(String id) {
        return null;
    }

    @Override
    public UserDTO updateUserById(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO enableDisableUser(UserDTO user) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public void sendResetPassword() {

    }

    @Override
    public void updateUserCredentials(UserDTO user) {

    }

    @Override
    public UserDTO getMe() {
        return null;
    }

    @Override
    public void resetPassword() {

    }
}
