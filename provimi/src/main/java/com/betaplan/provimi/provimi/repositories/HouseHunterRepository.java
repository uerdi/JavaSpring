package com.betaplan.provimi.provimi.repositories;

import com.betaplan.provimi.provimi.models.HouseHunter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseHunterRepository extends CrudRepository <HouseHunter,Long> {
    List<HouseHunter> findAll();
}
