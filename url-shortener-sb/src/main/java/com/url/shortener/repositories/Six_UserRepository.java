package com.url.shortener.repositories;

import com.url.shortener.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Six_UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);


}
