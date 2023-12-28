package com.betaplan.uerdi.dojosandninjas.repositories;

import com.betaplan.uerdi.dojosandninjas.models.Dojo;
import com.betaplan.uerdi.dojosandninjas.models.Ninja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NinjaRepository extends CrudRepository<Ninja,Long> {
    List<Ninja> findAll();
    List<Ninja> findAllByDojo(Dojo dojo);
}