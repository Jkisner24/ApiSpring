package com.exampleRest.service;

import com.exampleRest.entities.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    List<Owner> findAll();
    Optional<Owner> findById(Long id);
    void save(Owner owner);
    void deleteById(Long id);
}
