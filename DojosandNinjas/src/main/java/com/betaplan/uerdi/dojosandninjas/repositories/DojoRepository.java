package com.betaplan.uerdi.dojosandninjas.repositories;

import com.betaplan.uerdi.dojosandninjas.models.Dojo;
import com.betaplan.uerdi.dojosandninjas.models.Ninja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DojoRepository extends CrudRepository<Dojo, Long> {
    List<Dojo> findAll();

}
