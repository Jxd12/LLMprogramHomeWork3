package org.example.service.Impl;

import org.example.entity.PO.UserPO;
import org.example.entity.VO.UserSecurityVO;
import org.example.exception.InvalidPasswordException;
import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public UserSecurityVO login(UserSecurityVO user) {
        Optional<UserPO> optionalUser = userRepository.findByUsername(user.getUsername());

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException(user.getUsername());
        }

        UserPO existingUser = optionalUser.get();
        if (!Objects.equals(user.getPassword(), existingUser.getPassword())) {
            throw new InvalidPasswordException();
        }

        UserSecurityVO result = new UserSecurityVO();
        result.setUsername(existingUser.getUsername());
        result.setRole(existingUser.getRole());
        return result;
    }

    @Override
    public UserSecurityVO register(UserSecurityVO user) {
        Optional<UserPO> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        UserPO newUser = new UserPO();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole() != null ? user.getRole() : "USER");

        UserPO savedUser = userRepository.save(newUser);

        UserSecurityVO result = new UserSecurityVO();
        result.setUsername(savedUser.getUsername());
        result.setRole(savedUser.getRole());

        return result;
    }
}