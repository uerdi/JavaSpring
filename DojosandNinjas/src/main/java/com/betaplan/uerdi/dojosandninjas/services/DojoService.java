package com.betaplan.uerdi.dojosandninjas.services;

import com.betaplan.uerdi.dojosandninjas.models.Dojo;
import com.betaplan.uerdi.dojosandninjas.repositories.DojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DojoService {

  @Autowired
  private DojoRepository dojoRepository;

    public List<Dojo> findAll(){
        return dojoRepository.findAll();
    }

    public Dojo create(Dojo dojo) {
        System.out.println(dojo.getId());
        return dojoRepository.save(dojo);
    }

    public Dojo find(Long id) {
        Optional<Dojo> result = dojoRepository.findById(id);
        return result.orElse(null);
    }
}

