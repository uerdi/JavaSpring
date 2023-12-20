package com.betaplan.uerdi.dojosandninjas.services;

import com.betaplan.uerdi.dojosandninjas.models.Ninja;
import com.betaplan.uerdi.dojosandninjas.repositories.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

@Autowired
private NinjaRepository ninjaRepository;

    public List<Ninja> getAllNinjas() {
        return ninjaRepository.findAll();
    }



    public List<Ninja> all() {
        return ninjaRepository.findAll();
    }

    public Ninja create(Ninja ninja) {
        return ninjaRepository.save(ninja);
    }

    public Ninja find(Long Id) {
        Optional<Ninja> result = ninjaRepository.findById(Id);
        return result.orElse(null);
    }
}
