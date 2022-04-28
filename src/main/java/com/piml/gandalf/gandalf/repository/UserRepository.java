package com.piml.gandalf.gandalf.repository;
import com.piml.gandalf.gandalf.dto.UserDTO;
import com.piml.gandalf.gandalf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findTopByCpfOrUsernameOrEmail(String cpf, String username, String email);
}