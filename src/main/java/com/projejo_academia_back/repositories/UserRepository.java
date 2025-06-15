package com.projejo_academia_back.repositories;

import com.projejo_academia_back.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Users, String> {
    UserDetails findByEmail(String email);
}
