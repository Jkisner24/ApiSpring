package com.exampleRest.repository;

import com.exampleRest.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Owner, Long> {
}
