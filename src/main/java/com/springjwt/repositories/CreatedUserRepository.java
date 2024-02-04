package com.springjwt.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springjwt.entities.CreatedUser;

@Repository
public interface CreatedUserRepository extends JpaRepository<CreatedUser,Long> {

	Optional<CreatedUser> findByEmail(String email);
}
