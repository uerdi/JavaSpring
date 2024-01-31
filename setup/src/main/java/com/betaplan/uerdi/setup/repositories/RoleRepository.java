package com.betaplan.uerdi.setup.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

// imports removed for brevity
@Repository
public interface RoleRepository extends CrudRepository {
    List<Role> findAll();

    List<Role> findByName(String name);
}


