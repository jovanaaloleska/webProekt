package vebProektEshop.service;

import vebProektEshop.model.User;

public interface AuthService {
    User login(String username, String password);
}
