package com.betaplan.uerdi.quotedash.services;


import com.betaplan.uerdi.quotedash.models.LoginUser;
import com.betaplan.uerdi.quotedash.models.User;
import com.betaplan.uerdi.quotedash.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User newUser, BindingResult result) {
        Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());

        if (potentialUser.isPresent()) {
            result.rejectValue("email", "EmailTaken", "Email address is already in use");
        }

        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "matches", "The Confirm Password must match Password");
        }

        if (result.hasErrors()) {
            return null;
        } else {
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepository.save(newUser);
        }
    }

    public User login(LoginUser newLoginObject, BindingResult result) {
        Optional<User> potentialUser = userRepository.findByEmail(newLoginObject.getEmail());

        if (!potentialUser.isPresent()) {
            result.rejectValue("email", "EmailNotFound", "No User Found with that Email address");
        } else {
            if (!BCrypt.checkpw(newLoginObject.getPassword(), potentialUser.get().getPassword())) {
                result.rejectValue("password", "Matches", "Invalid Password");
            }
        }

        if (result.hasErrors()) {
            return null;
        } else {
            return potentialUser.get();
        }
    }

    public User findOneUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }
}
