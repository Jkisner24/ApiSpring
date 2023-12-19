package com.exampleRest.persistence.impl;

import com.exampleRest.entities.Owner;
import com.exampleRest.persistence.UserDAO;
import com.exampleRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Owner> findAll() {
        return (List<Owner>) userRepository.findAll();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(Owner owner) {
        userRepository.save(owner);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
