package com.oshovskii.spring.security.repositories;

import com.oshovskii.spring.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUserId(Long id);     // большие проблемы угадывания имени метода =(
}

