package com.betaplan.provimi.provimi.repositories;

import com.betaplan.provimi.provimi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
