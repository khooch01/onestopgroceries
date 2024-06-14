package com.khooch.onestopgroceries.service;

import com.khooch.onestopgroceries.dto.UserRegistrationDto;
import com.khooch.onestopgroceries.entity.Role;
import com.khooch.onestopgroceries.entity.User;
import com.khooch.onestopgroceries.repository.RoleRepository;
import com.khooch.onestopgroceries.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(UserRegistrationDto registrationDto) {
        Optional<Role> viewStoreRoleOpt = roleRepository.findById(1L);
        if (!viewStoreRoleOpt.isPresent()) {
            throw new RuntimeException("VIEW_STORE role not found");
        }
        Role viewStoreRole = viewStoreRoleOpt.get();

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRoles(Collections.singletonList(viewStoreRole));
        userRepository.save(user);
    }
}
