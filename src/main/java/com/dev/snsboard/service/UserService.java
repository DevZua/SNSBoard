package com.dev.snsboard.service;

import com.dev.snsboard.exception.user.UserAlreadyExistsException;
import com.dev.snsboard.exception.user.UserNotFoundException;
import com.dev.snsboard.model.entity.UserEntity;
import com.dev.snsboard.model.user.User;
import com.dev.snsboard.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserEntityRepository userEntityRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public User signUp(String username, String password) {
        userEntityRepository
                .findByUsername(username)
                .ifPresent(
                        user -> {
                            throw new UserAlreadyExistsException();
                        });


        var userEntity = UserEntity.of(username, password);
        var savedUserEntity = userEntityRepository.save(userEntity);

        return User.from(savedUserEntity);
    }
}
