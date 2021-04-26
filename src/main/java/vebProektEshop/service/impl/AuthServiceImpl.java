package vebProektEshop.service.impl;

import vebProektEshop.model.User;
import vebProektEshop.model.exceptions.InvalidArgumentsException;
import vebProektEshop.model.exceptions.InvalidUserCredentialsException;
import vebProektEshop.repository.UserRepository;
import vebProektEshop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
