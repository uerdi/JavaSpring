package com.betaplan.provimi.provimi.services;

import com.betaplan.provimi.provimi.models.HouseHunter;
import com.betaplan.provimi.provimi.repositories.HouseHunterRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class HouseHunterService {
    @Autowired
    private HouseHunterRepository repo;

    public HouseHunter findById(Long id) {
        Optional<HouseHunter> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public HouseHunter update(HouseHunter hunter) {
        return repo.save(hunter);
    }
    public List<HouseHunter> all() {
        return repo.findAll();
    }

    public HouseHunter create(HouseHunter houseHunter) {
        return repo.save(houseHunter);
    }

    public void deleteHouseHunter(HouseHunter hunter) {

        repo.delete(hunter);
    }

}

