package com.exampleRest.service.impl;

import com.exampleRest.entities.Owner;
import com.exampleRest.persistence.UserDAO;
import com.exampleRest.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Owner> findAll() {
        return (List<Owner>) userDAO.findAll();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public void save(Owner owner) {
        userDAO.save(owner);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }
}
