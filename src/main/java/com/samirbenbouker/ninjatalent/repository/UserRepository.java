package com.samirbenbouker.ninjatalent.repository;

import com.samirbenbouker.ninjatalent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // query to check if a user contains the entered email
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);
}
