package org.example.teamwork.repository;

import org.example.teamwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
